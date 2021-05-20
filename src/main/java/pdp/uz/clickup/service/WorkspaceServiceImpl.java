package pdp.uz.clickup.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pdp.uz.clickup.entity.*;
import pdp.uz.clickup.entity.enums.AddType;
import pdp.uz.clickup.entity.enums.WorkspacePermissionName;
import pdp.uz.clickup.entity.enums.WorkspaceRoleName;
import pdp.uz.clickup.payload.*;
import pdp.uz.clickup.repository.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    final WorkspaceRepository workspaceRepository;
    final UserRepository userRepository;
    final AttachmentRepository attachmentRepository;
    final WorkspaceUserRepository workspaceUserRepository;
    final WorkspaceRoleRepository workspaceRoleRepository;
    final WorkspacePermissionRepository workspacePermissionRepository;
    final JavaMailSender javaMailSender;

    public WorkspaceServiceImpl(WorkspaceRepository workspaceRepository, UserRepository userRepository,
                                AttachmentRepository attachmentRepository, WorkspaceUserRepository workspaceUserRepository,
                                WorkspaceRoleRepository workspaceRoleRepository, WorkspacePermissionRepository workspacePermissionRepository,
                                JavaMailSender javaMailSender) {
        this.workspaceRepository = workspaceRepository;
        this.userRepository = userRepository;
        this.attachmentRepository = attachmentRepository;
        this.workspaceUserRepository = workspaceUserRepository;
        this.workspaceRoleRepository = workspaceRoleRepository;
        this.workspacePermissionRepository = workspacePermissionRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public ApiResponse addWorkspace(WorkspaceDTO workspaceDTO, User user) {

        // Creating Workspace
        if (workspaceRepository.existsByIdAndName(user.getId(), workspaceDTO.getName())) {
            return new ApiResponse("You've already owned the workspace", false);
        }

        WorkSpace workSpace = new WorkSpace(
                workspaceDTO.getName(),
                workspaceDTO.getColor(),
                user,
                workspaceDTO.getAttachmentId() == null ? null : attachmentRepository.findById(workspaceDTO.getAttachmentId()).get()
        );
        workspaceRepository.save(workSpace);

        // Creating WorkspaceRole
        WorkspaceRole ownerRole = workspaceRoleRepository.save(new WorkspaceRole(WorkspaceRoleName.ROLE_OWNER.name(), workSpace, null));

        WorkspaceRole adminRole = workspaceRoleRepository.save(new WorkspaceRole(WorkspaceRoleName.ROLE_ADMIN.name(), workSpace, null));
        WorkspaceRole memberRole = workspaceRoleRepository.save(new WorkspaceRole(WorkspaceRoleName.ROLE_MEMBER.name(), workSpace, null));
        WorkspaceRole guestRole = workspaceRoleRepository.save(new WorkspaceRole(WorkspaceRoleName.ROLE_GUEST.name(), workSpace, null));

        //Giving Permissions to Owner
        WorkspacePermissionName[] workspacePermissionNames = WorkspacePermissionName.values();
        List<WorkspacePermission> workspacePermissionList = new ArrayList<>();

        for (WorkspacePermissionName workspacePermissionName : workspacePermissionNames) {

            WorkspacePermission workspacePermission = new WorkspacePermission(ownerRole, workspacePermissionName);
            workspacePermissionList.add(workspacePermission);

            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissionList.add(new WorkspacePermission(adminRole, workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissionList.add(new WorkspacePermission(memberRole, workspacePermissionName));
            }
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissionList.add(new WorkspacePermission(guestRole, workspacePermissionName));
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissionList);

        //Creating WorkspaceUser
        workspaceUserRepository.save(new WorkSpaceUser(workSpace, user, ownerRole,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())));

        return new ApiResponse("New Workspace saved", true);
    }

    @Override
    public ApiResponse editWorkspace(Long id, WorkspaceDTO workspaceDTO) {

        Optional<WorkSpace> optionalWorkSpace = workspaceRepository.findById(id);
        if (!optionalWorkSpace.isPresent())
            return new ApiResponse("Invalid Workspace Id", false);

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(workspaceDTO.getAttachmentId());
        Attachment attachment;
        attachment = optionalAttachment.orElse(null);

        WorkSpace workSpace = optionalWorkSpace.get();
        workSpace.setColor(workSpace.getColor());
        workSpace.setAvatarId(attachment);
        workSpace.setName(workspaceDTO.getName());
        workspaceRepository.save(workSpace);
        return new ApiResponse("Workspace Edited", true);
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, Long newOwnerId) {

        /**Ownerni o'zgartirishda workspaceId va yangi ownerId request bilan keladi
         * Kelgan requestdan workspace mavjutligi tekshiriladi,
         * Shu workspace ning o'zgartirilishdan oldingi ownerining id si  @Query yordamida aniqlanib olinadi
         * va shu id si orqali owner member lavozimiga otqazilib qo'yiladi
         * Keyin yangi owner lavozimi beriladi
         * Bu yerda ROLE_OWNER va ROLE_MEMBER lar DB dan olib kelib ishlangan,
         * Workspace_role table da Owner bilan Member ninging Id lari o'zgarib qolish mumkunligi uchu shunday qilingan*/

        Optional<WorkSpace> optionalWorkSpace = workspaceRepository.findById(id);
        if (!optionalWorkSpace.isPresent())
            return new ApiResponse("Invalid Workspace Id!", false);

        //workspaceRole table dan Owner id va member id topib olindi
        Long oldOwnerId = workspaceRoleRepository.finOwnerByWorkSpaceId(id);
        Long memberRoleId = workspaceRoleRepository.memberRoleId();

        //DG dan ROLE_OWNER va ROLE_MEMBER
        Optional<WorkspaceRole> workspaceOwner = workspaceRoleRepository.findById(oldOwnerId);
        Optional<WorkspaceRole> memberRole = workspaceRoleRepository.findById(memberRoleId);

        //owner vazifasini bajarayotgan user topib olindi
        Optional<WorkSpaceUser> oldOwner = workspaceUserRepository.findById(oldOwnerId);
        if (!workspaceOwner.isPresent() || !oldOwner.isPresent())
            return new ApiResponse("Invalid WorkspaceOwner or Owner Id!", false);

        //yangi owner id orqali topib olindi
        Optional<WorkSpaceUser> optionalNewOwner = workspaceUserRepository.findById(newOwnerId);
        if (!memberRole.isPresent() || !optionalNewOwner.isPresent())
            return new ApiResponse("There is not member yet in this workplace", false);

        WorkSpaceUser newOwner2 = optionalNewOwner.get();
        newOwner2.setWorkSpaceRole(workspaceOwner.get());
        workspaceUserRepository.save(newOwner2);

        WorkSpaceUser oldOwner2 = oldOwner.get();
        oldOwner2.setWorkSpaceRole(memberRole.get());
        workspaceUserRepository.save(oldOwner2);
        return new ApiResponse("Owner role changed to other member", true);
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {

        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("Workspace deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Workspace don't deleted", false);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDTO memberDTO) {

        if (memberDTO.getAddType().equals(AddType.ADD)) {

            Optional<User> optionalUser = userRepository.findById(memberDTO.getRoleId());
            if (!optionalUser.isPresent())
                return new ApiResponse("Invalid User Id!", false);
            User user = optionalUser.get();

            WorkSpaceUser workSpaceUser = new WorkSpaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id")),
                    userRepository.findById(memberDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("id")),
                    new Timestamp((System.currentTimeMillis())),
                    null
            );
            workspaceUserRepository.save(workSpaceUser);
            int code = new Random().nextInt(999999);
            user.setEmailCode(String.valueOf(code).substring(0, 4));
            userRepository.save(user);
            sendEmail(user.getEmail(), user.getEmailCode());

        } else if (memberDTO.getAddType().equals(AddType.EDIT)) {

            WorkSpaceUser workSpaceUser = workspaceUserRepository.findByWorkSpaceIdAndUserId(id, memberDTO.getId()).orElseGet(WorkSpaceUser::new);
            workSpaceUser.setWorkSpaceRole(workspaceRoleRepository.findById(memberDTO.getRoleId()).orElseThrow(
                    () -> new ResourceNotFoundException("id")));

            workspaceUserRepository.save(workSpaceUser);

        } else if (memberDTO.getAddType().equals(AddType.REMOVE))

            workspaceUserRepository.deleteByWorkSpaceIdAndUserId(id, memberDTO.getId());
        return new ApiResponse("Successfully", true);
    }

    @Override
    public ApiResponse joinToWorkspace(Long id, User user) {

        Optional<WorkSpaceUser> optionalWorkspaceUser = workspaceUserRepository.findByWorkSpaceIdAndUserId(id, user.getId());
        if (optionalWorkspaceUser.isPresent()) {
            WorkSpaceUser workspaceUser = optionalWorkspaceUser.get();
            workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
            workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Error", false);
    }

    @Override
    public List<User> getMemberList(Long workspaceId) {
        return userRepository.getMemberList(workspaceId);
    }

    @Override
    public List<User> getWorkspaceGuestList(Long workspaceId) {
        return userRepository.getGuestList(workspaceId);
    }

    @Override
    public List<WorkSpace> getWorkspaceList(User user) {

        List<WorkSpaceUser> workSpaceUsers = workspaceUserRepository.findAllByUserId(user.getId());
        return workSpaceUsers.stream().map(workSpaceUser -> mapWorkspaceToWorkSpace(workSpaceUser.getWorkSpace())).collect(Collectors.toList());
    }

    @Override
    public ApiResponse addRole(WorkspaceRoleDTO workspaceRoleDTO) {

        Optional<WorkSpace> optionalWorkSpace = workspaceRepository.findById(workspaceRoleDTO.getWorkSpaceId());
        if (!optionalWorkSpace.isPresent())
            return new ApiResponse("Invalid Workspace Id!", false);

        boolean exists = workspaceRoleRepository.existsByNameAndWorkSpaceId(workspaceRoleDTO.getName(), workspaceRoleDTO.getWorkSpaceId());
        if (exists) {
            return new ApiResponse("The Role already exist in this Workspace!", false);
        }

        WorkspaceRole workspaceRole = new WorkspaceRole(
                workspaceRoleDTO.getName(), optionalWorkSpace.get(), null
        );
        return new ApiResponse("New Role Added To Workspace", true);
    }

    @Override
    public ApiResponse addPermissionToRole(Long roleId, RolePermissionDTO rolePermissionDTO) {


        if (rolePermissionDTO.getAddOrDelete().equals(AddType.ADD.toString())) {

            Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(roleId);
            if (!optionalWorkspaceRole.isPresent())
                return new ApiResponse("Invalid WorkspaceRole Id!", false);

            List<WorkspacePermission> workspacePermissionList = new ArrayList<>();
            for (WorkspacePermissionName permission : rolePermissionDTO.getWorkspacePermissionNameList()) {

                boolean permissionOptional = workspacePermissionRepository.existsByWorkspacePermission(permission);
                if (!permissionOptional)
                    return new ApiResponse("Invalid Permission Id!", false);

                boolean exists = workspacePermissionRepository.existsByWorkspacePermissionAndWorkSpaceRole(permission, optionalWorkspaceRole.get());
                if (exists)
                    return new ApiResponse("This permission Already exist on this Role!", false);
                WorkspacePermission workspacePermission = new WorkspacePermission(optionalWorkspaceRole.get(), permission);
                workspacePermissionList.add(workspacePermission);
            }
            workspacePermissionRepository.saveAll(workspacePermissionList);

        }
        if (rolePermissionDTO.getAddOrDelete().equals(AddType.REMOVE.toString())) {

            Optional<WorkspaceRole> optionalWorkspaceRole = workspaceRoleRepository.findById(roleId);
            if (!optionalWorkspaceRole.isPresent())
                return new ApiResponse("Invalid WorkspaceRole Id!", false);

            List<WorkspacePermissionName> workspacePermissionNameList = rolePermissionDTO.getWorkspacePermissionNameList();
            for (WorkspacePermissionName workspacePermissionName : workspacePermissionNameList) {
                workspacePermissionRepository.deleteByWorkSpaceRoleIdAndWorkspacePermission(roleId, workspacePermissionName);
            }
        }
        return new ApiResponse("Role Permission(s) edited!", true);
    }

    @Override
    public List<MemberDTO> getMemberAndGuest(Long workSpaceId) {
        List<WorkSpaceUser> workSpaceUsers = workspaceUserRepository.findByWorkSpaceId(workSpaceId);

        /**
         * List<MemberDTO> memberDTOList = new ArrayList<>();
         for (WorkSpaceUser workSpaceUser : workSpaceUsers) {
         MemberDTO memberDTO = mapWorkSpaceUserToMemberDTO(workSpaceUser);
         memberDTOList.add(memberDTO);
         }
         return memberDTOList;*/
        return workSpaceUsers.stream().map(this::mapWorkSpaceUserToMemberDTO).collect(Collectors.toList());
    }


    /**
     * My own method
     */
    public MemberDTO mapWorkSpaceUserToMemberDTO(WorkSpaceUser workSpaceUser) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(workSpaceUser.getUser().getId());
        memberDTO.setFullName(memberDTO.getFullName());
        memberDTO.setEmail(memberDTO.getEmail());
        memberDTO.setRoleName(workSpaceUser.getWorkSpaceRole().getName());
        memberDTO.setLastActiveTime(workSpaceUser.getUser().getLastActiveTime());
        return memberDTO;
    }

    public WorkSpace mapWorkspaceToWorkSpace(WorkSpace workSpace) {

        WorkSpace workSpaceNew = new WorkSpace();
        workSpace.setId(workSpace.getId());
        workSpace.setInitialLetter();
        workSpace.setAvatarId(workSpace.getAvatarId() == null ? null : workSpace.getAvatarId());
        workSpace.setColor(workSpace.getColor());
        workSpace.setName(workSpace.getName());

        return workSpace;
    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Test@pdp.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Account Header");
            mailMessage.setText(emailCode);
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /*@Override
    public ApiResponse addMember(AddMemberDTO addMemberDTO) {

        Optional<WorkSpace> optionalWorkSpace = workspaceRepository.findById(addMemberDTO.getWorkspaceId());
        if (!optionalWorkSpace.isPresent())
            return new ApiResponse("Invalid Workspace Id!", false);

        Optional<User> optionalUser = userRepository.findByEmail(addMemberDTO.getEmail());
        if (!optionalUser.isPresent())
            return new ApiResponse("Invalid User Id!", false);

        Optional<WorkspaceRole> roleOptional = workspaceRoleRepository.findById(addMemberDTO.getRoleId());
        if (!roleOptional.isPresent())
            return new ApiResponse("Invalid Role Id!", false);

        Optional<WorkSpaceUser> workSpaceUser = workspaceUserRepository
                .findByWorkSpaceIdAndUserId(addMemberDTO.getWorkspaceId(), optionalUser.get().getId());

        if (workSpaceUser.isPresent())
            return new ApiResponse("This user already exist in this workspace!", false);

        WorkSpaceUser newWorkSpaceUser = new
                WorkSpaceUser(optionalWorkSpace.get(),
                optionalUser.get(),
                roleOptional.get(),
                new Timestamp((System.currentTimeMillis())),
                null
                );
        return null;
    }*/
}
