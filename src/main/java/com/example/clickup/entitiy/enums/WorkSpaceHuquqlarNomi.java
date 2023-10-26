package com.example.clickup.entitiy.enums;

import java.util.Arrays;
import java.util.List;

public enum WorkSpaceHuquqlarNomi {
    ADD_REMOVE_MEMBER("Add/Remove Members", "Workspacega memberslarni qo'shadi yoki o'chiradi", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    GIT("Git", "Userlarga tasklarni GitHub, BitBucket", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    EDIT_STATUS("Edit", "Statuslarni edit qilish", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    MANAGE_TASK("ManageTask", "Foydalanuvchiga teglarni yaratish, tahrirlash va oʻchirishga ruxsat beradi.", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    SEND_EMAIL("SendEmail", "Foydalanuvchiga Email ClickApp yordamida elektron pochta xabarlarini yuborishga ruxsat beradi.", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    ADD_EMAIL_ACCOUNT("AddEmailAccount", "Foydalanuvchiga Email ClickApp yordamida vakolatli elektron pochta hisoblarini qo'shishga ruxsat beradi.", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    CUSTOM_ROLES("CustoRoles", "Foydalanuvchiga barcha maxsus rollarni yaratish, tahrirlash, o'chirish va boshqarish imkoniyatini beradi. ", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    CREATE_SPACE("CreateSpace", "Foydalanuvchiga Workspace jamoasida Spaces yaratishga ruxsat beradi.", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    CREATE_VIEWS("CreateViews", "Foydalanuvchiga joylardagi ko'rinishlarni yaratish va tahrirlash uchun ruxsat beradi.", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    DELETE_ITEMS("DeleteItems", "Foydalanuvchiga elementlarni o'chirishga ruxsat beradi. ", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER)),
    CREATE_WORKSPACE("CreateWorksapce", "Statuslarni edit qilish", Arrays.asList(WorkSpaceLavozimNomlari.OWNER, WorkSpaceLavozimNomlari.ADMIN, WorkSpaceLavozimNomlari.MEMBER));

    String nomi;
    String izoh;
    List<WorkSpaceLavozimNomlari> workSpaceLavozimNomlariList;

    WorkSpaceHuquqlarNomi(String nomi, String izoh, List<WorkSpaceLavozimNomlari> workSpaceLavozimNomlariList) {
        this.nomi = nomi;
        this.izoh = izoh;
        this.workSpaceLavozimNomlariList = workSpaceLavozimNomlariList;
    }

    public String getNomi() {
        return nomi;
    }

    public String getIzoh() {
        return izoh;
    }

    public List<WorkSpaceLavozimNomlari> getWorkSpaceLavozimNomlariList() {
        return workSpaceLavozimNomlariList;
    }
}
