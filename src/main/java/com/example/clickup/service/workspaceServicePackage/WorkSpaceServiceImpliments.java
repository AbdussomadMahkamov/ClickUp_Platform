package com.example.clickup.service.workspaceServicePackage;

import com.example.clickup.entitiy.*;
import com.example.clickup.entitiy.enums.BuyruqTuri;
import com.example.clickup.entitiy.enums.PlatformaLavozimlari;
import com.example.clickup.entitiy.enums.WorkSpaceHuquqlarNomi;
import com.example.clickup.entitiy.enums.WorkSpaceLavozimNomlari;
import com.example.clickup.entitiy.workspace.*;
import com.example.clickup.payload.ApiResponse;
import com.example.clickup.payload.UsersDto;
import com.example.clickup.payload.WorkSpaceUsersDto;
import com.example.clickup.payload.WorkSpaceDto;
import com.example.clickup.repository.*;
import com.example.clickup.repository.workspaceRepo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkSpaceServiceImpliments implements WorkSpaceService{
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    WorkSpaceRepository workSpaceRepository;
    @Autowired
    IshchilarSoniRepository ishchilarSoniRepository;
    @Autowired
    ReklamaRepository reklamaRepository;
    @Autowired
    WorkSpaceLavozimlariRepository workSpaceLavozimlariRepository;
    @Autowired
    WorkSpaceUsersRepository workSpaceUsersRepository;
    @Autowired
    WorkSpaceHuquqlariRepository workSpaceHuquqlariRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ReserveUsersRepository reserveUsersRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public ApiResponse addWorkSpace(WorkSpaceDto workSpaceDto, Users users) {
//        Users users1 = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(users.toString());
        if (workSpaceRepository.existsByNomiAndUsersId(workSpaceDto.getNomi(), users.getId())){
            return new ApiResponse("Kechirasiz siz oldin ham ushbu nom ostida WorkSpace yaratgansiz! Boshqa nom tanlang.", false);
        }
        Optional<IshchilarSoni> byIshchilarSoni = ishchilarSoniRepository.findByIshchilarSoni(workSpaceDto.getIshchilarSoni());
        if (!byIshchilarSoni.isPresent()){
            return new ApiResponse("Bunday ishchilar soni mavjud emas!", false);
        }
        Optional<Reklama> byReklamaNomi = reklamaRepository.findByNomi(workSpaceDto.getReklamaNomi());
        if (!byReklamaNomi.isPresent()){
            return new ApiResponse("Bunday reklama nomi topilmadi!", false);
        }
        WorkSpace workSpace=new WorkSpace(
                workSpaceDto.getNomi(),
                workSpaceDto.getProfilRangi(),
                workSpaceDto.getIshXonaRangi(),
                byIshchilarSoni.get(),
                users,
                byReklamaNomi.get()
        );
        workSpaceRepository.save(workSpace);
        WorkSpaceLavozimlari owner = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.OWNER.name()));
        WorkSpaceLavozimlari admin = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.ADMIN.name()));
        WorkSpaceLavozimlari member = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.MEMBER.name()));
        WorkSpaceLavozimlari guest = workSpaceLavozimlariRepository.save(new WorkSpaceLavozimlari(workSpace, WorkSpaceLavozimNomlari.GUEST.name()));
        WorkSpaceHuquqlarNomi[] values = WorkSpaceHuquqlarNomi.values();
        List<WorkSpaceHuquqlari> workSpaceHuquqlariList=new ArrayList<>();
        for (WorkSpaceHuquqlarNomi value : values) {
            WorkSpaceHuquqlari workSpaceHuquqlari=new WorkSpaceHuquqlari(
                    owner,
                    value
            );
            workSpaceHuquqlariList.add(workSpaceHuquqlari);
            if (value.getWorkSpaceLavozimNomlariList().equals(WorkSpaceLavozimNomlari.ADMIN)){
                WorkSpaceHuquqlari workSpaceHuquqlari1=new WorkSpaceHuquqlari(
                        admin,
                        value
                );
                workSpaceHuquqlariList.add(workSpaceHuquqlari1);
            }
            if (value.getWorkSpaceLavozimNomlariList().equals(WorkSpaceLavozimNomlari.MEMBER)){
                WorkSpaceHuquqlari workSpaceHuquqlari1=new WorkSpaceHuquqlari(
                        member,
                        value
                );
                workSpaceHuquqlariList.add(workSpaceHuquqlari1);
            }
            if (value.getWorkSpaceLavozimNomlariList().equals(WorkSpaceLavozimNomlari.GUEST)){
                WorkSpaceHuquqlari workSpaceHuquqlari1=new WorkSpaceHuquqlari(
                        guest,
                        value
                );
                workSpaceHuquqlariList.add(workSpaceHuquqlari1);
            }
        }
        workSpaceHuquqlariRepository.saveAll(workSpaceHuquqlariList);
        workSpaceUsersRepository.save(new WorkSpaceUsers(
                users,
                workSpace,
                owner,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        ));
        return new ApiResponse("Yangi WorkSpace yaratildi.", true);
    }

    @Override
    public ApiResponse addEditRemove(WorkSpaceUsersDto workSpaceUsersDto, Long workspaceId, Long lavozimId) {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<WorkSpaceUsers> b = workSpaceUsersRepository.findByUsersIdAndWorkSpaceId(users.getId(), workspaceId);
        if (!b.isPresent()){
            return new ApiResponse("Siz bu workspaceda ishlay olmaysiz!", false);
        }
        Optional<WorkSpace> byId = workSpaceRepository.findById(workspaceId);
        if (!byId.isPresent()){
            return new ApiResponse("Kechirasiz bunday idli workspace topilmadi", false);
        }
        Optional<Users> byUsername = usersRepository.findByUsername(workSpaceUsersDto.getUsername());
        Optional<WorkSpaceLavozimlari> byId1 = workSpaceLavozimlariRepository.findById(lavozimId);
        if (!byId1.isPresent()){
            return new ApiResponse("Kechirasiz bunday idli lavozim bazada mavjud emas!", false);
        }
        if ((b.isPresent() || byUsername.isPresent()) && !b.get().getWorkSpaceLavozimlari().equals(WorkSpaceLavozimNomlari.GUEST)){
            if (workSpaceUsersDto.getBuyruqTuri().equals(BuyruqTuri.QOSHISH)){
                if (!byId1.isPresent()){
                    return new ApiResponse("Kechirasiz bunday idli lavozim mavjud emas!", false);
                }
                if (byUsername.isPresent()){
                    WorkSpaceUsers workSpaceUsers = new WorkSpaceUsers(
                            byUsername.get(),
                            byId.get(),
                            byId1.get(),
                            new Timestamp(System.currentTimeMillis()),
                            null
                    );
                    if (XabarYuborish(workSpaceUsersDto.getUsername(), workspaceId)){
                        workSpaceUsersRepository.save(workSpaceUsers);
                        return new ApiResponse("Taklif havolasi emal pochtaga xabar yuborildi.", true);
                    }
                    return new ApiResponse("Taklif xabari yuborilmadi", false);
                }
                ReserveUsers reserveUsers = new ReserveUsers(
                        workSpaceUsersDto.getUsername(),
                        byId.get(),
                        byId1.get()
                );
                if (XabarYuborish(workSpaceUsersDto.getUsername(), workspaceId)){
                    reserveUsersRepository.save(reserveUsers);
                    return new ApiResponse("Taklif xavolasi email pochtaga yuborildi. Foydalanuvchi tizmidan ro'yxatdan o'tib WorkSpacega qo'shilishi mumkin.", true);
                }
                return new ApiResponse("Taklif xavolasi yuborilmadi! Email adressni tekshirib qayta ko'ring!", false);
            }
            else if (workSpaceUsersDto.getBuyruqTuri().equals(BuyruqTuri.TAHRIRLASH)) {
                Optional<WorkSpaceUsers> foydalanuvchi = workSpaceUsersRepository.findByUsersIdAndWorkSpaceId(byUsername.get().getId(), workspaceId);
                if (!foydalanuvchi.isPresent()){
                    return new ApiResponse("Kechirasiz bunday foydalanuvchi bu workspaceda mavjud emas!", false);
                }
                WorkSpaceUsers workSpaceUsers = foydalanuvchi.get();
                workSpaceUsers.setWorkSpaceLavozimlari(byId1.get());
                workSpaceUsersRepository.save(workSpaceUsers);
                return new ApiResponse("Siz "+byUsername.get().getUsername()+"ni lavozimini "+byId1.get().getLavozmiNomlari()+"ga o'zgartirdingiz!", true);
            }
            else if (workSpaceUsersDto.getBuyruqTuri().equals(BuyruqTuri.OCHIRISH)) {
                Optional<WorkSpaceUsers> foydalanuvchi = workSpaceUsersRepository.findByUsersIdAndWorkSpaceId(byUsername.get().getId(), workspaceId);
                if (!foydalanuvchi.isPresent()){
                    return new ApiResponse("Kechirasiz bunday foydalanuvchi bu workspaceda mavjud emas!", false);
                }
                workSpaceUsersRepository.deleteById(foydalanuvchi.get().getId());
                return new ApiResponse("Siz "+foydalanuvchi.get().getWorkSpace().getNomi()+"dan "+foydalanuvchi.get().getUsers().getFish()+" ni o'chirdingiz", true);
            }
            return new ApiResponse("Byruq mos kelmadi", false);
        }
        ReserveUsers reserveUsers = new ReserveUsers(workSpaceUsersDto.getUsername(), byId.get(), byId1.get());
        if (XabarYuborish(workSpaceUsersDto.getUsername(), workspaceId)){
            reserveUsersRepository.save(reserveUsers);
            return new ApiResponse("Taklif xavolasi email pochtaga yuborildi. Foydalanuvchi tizmidan ro'yxatdan o'tib WorkSpacega qo'shilishi mumkin.", true);
        }
        return new ApiResponse("Taklif xavolasi yuborilmadi! Email adressni tekshirib qayta ko'ring!", false);
    }
    public boolean XabarYuborish(String email, Long workspaceId){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setFrom("mahkamovabdussomad@gmail.com");
            simpleMailMessage.setSubject("WorkSpacega qo'shilish!");
            simpleMailMessage.setText("<a href='http://localhost:8080/workspace/joinWorkSpace?username="+email+"&workspaceid="+workspaceId+"'>Emailni tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        }catch (Exception e){
            e.getStackTrace();
            return false;
        }
    }

    @Override
    public ApiResponse joinUser(String username, Long workspaceid) {
        Optional<Users> byUsername = usersRepository.findByUsername(username);
        if (byUsername.isPresent()){
            Optional<WorkSpaceUsers> byUsersIdAndWorkSpaceId = workSpaceUsersRepository.findByUsersIdAndWorkSpaceId(byUsername.get().getId(), workspaceid);
            if (byUsername.isPresent() && byUsersIdAndWorkSpaceId.isPresent()){
                WorkSpaceUsers workSpaceUsers = byUsersIdAndWorkSpaceId.get();
                workSpaceUsers.setQabulQilinganVaqt(new Timestamp(System.currentTimeMillis()));
                workSpaceUsersRepository.save(workSpaceUsers);
                return new ApiResponse("Siz "+byUsersIdAndWorkSpaceId.get().getWorkSpace().getNomi()+" ishxonasiga "+byUsersIdAndWorkSpaceId.get().getWorkSpaceLavozimlari()+" lavozimda ishga qabul qilindingiz!", true);
            }

        }

        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(username);
            simpleMailMessage.setFrom("mahkamovabdussomad@gmail.com");
            simpleMailMessage.setSubject("Ro'yxatdan o'ting!");
            simpleMailMessage.setText("<a href='http://localhost:8080/workspace/signUpForJoin?workSpaceId="+workspaceid+"'>Emailni tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return new ApiResponse("Clik Up paltformasidan ro'yxatdan o'ting", false);
        }catch (Exception e){
            e.getStackTrace();
            return new ApiResponse();
        }
    }

    @Override
    public ApiResponse signUpForJoin(UsersDto usersDto, Long workSpaceId) {
            Optional<Users> byUsername = usersRepository.findByUsername(usersDto.getUsername());
            Optional<ReserveUsers> byUsername1 = reserveUsersRepository.findByUsername(usersDto.getUsername());
            Optional<WorkSpace> byId = workSpaceRepository.findById(workSpaceId);
            if (!byUsername.isPresent() && byUsername1.isPresent()){
                Users users = new Users(
                        usersDto.getFish(),
                        usersDto.getUsername(),
                        passwordEncoder.encode(usersDto.getPassword()),
                        PlatformaLavozimlari.SYSTEMUSER,
                        true
                );
                usersRepository.save(users);
                WorkSpaceUsers workspaceUser = workSpaceUsersRepository.save(new WorkSpaceUsers(
                        users,
                        byId.get(),
                        byUsername1.get().getWorkSpaceLavozimlari(),
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                ));
                reserveUsersRepository.deleteById(byUsername1.get().getId());
                return new ApiResponse("Siz "+byId.get().getNomi()+" ishxonasiga "+workspaceUser.getWorkSpaceLavozimlari() +" lavozimida qo'shildingiz",true);
            }
            return new ApiResponse("Sizga taklif yuborilmagan yoki bekor qilingan",false);

    }
}