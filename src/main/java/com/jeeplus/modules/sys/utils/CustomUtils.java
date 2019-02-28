package com.jeeplus.modules.sys.utils;

import com.hqu.modules.basedata.organizationmanagement.entity.OrganizationManagement;
import com.hqu.modules.basedata.organizationmanagement.mapper.OrganizationManagementMapper;
import com.hqu.modules.basedata.positionmanagement.entity.ProfessionalTechniqueRank;
import com.hqu.modules.basedata.positionmanagement.mapper.ProfessionalTechniqueRankMapper;
import com.hqu.modules.basedata.specialitymanage.entity.SpecialityManage;
import com.hqu.modules.basedata.specialitymanage.mapper.SpecialityManageMapper;
import com.jeeplus.common.utils.SpringContextHolder;
import com.jeeplus.modules.sys.mapper.UserMapper;

import javax.swing.*;

public class CustomUtils {
    private static UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private static SpecialityManageMapper specialityManageMapper = SpringContextHolder.getBean(SpecialityManageMapper.class);
    private static ProfessionalTechniqueRankMapper professionalTechniqueRankMapper = SpringContextHolder.getBean(ProfessionalTechniqueRankMapper.class);
    private static OrganizationManagementMapper organizationManagementMapper = SpringContextHolder.getBean(OrganizationManagementMapper.class);

    public static SpecialityManage getSpecialityManageByZYMC(String zymc) {
        SpecialityManage specialityManage = specialityManageMapper.getByZYMC(zymc);
        if(specialityManage == null) {
            specialityManage = new SpecialityManage();
        }
        return specialityManage;
    }

    public static ProfessionalTechniqueRank getProfessionalTechniqueRandByZYJSJBMC(String ZYJSJBMC){
        ProfessionalTechniqueRank professionalTechniqueRank =  professionalTechniqueRankMapper.getByZYJSJBMC(ZYJSJBMC);
        if(professionalTechniqueRank == null) {
            professionalTechniqueRank = new ProfessionalTechniqueRank();
        }
        return professionalTechniqueRank;
    }
    public static OrganizationManagement getOrganizationManagementByDWMC(String dwdm) {
        OrganizationManagement organizationManagement =organizationManagementMapper.getByDWMC(dwdm);
        if(organizationManagement == null) {
            organizationManagement = new OrganizationManagement();
        }
        return organizationManagement;
    }
}
