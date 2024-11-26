package com.ljrh.pmssocket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.ljrh.pmssocket.dao.IPmsUserDao;
import com.ljrh.pmssocket.entity.ThirdPmsUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PmsSocketApplicationTests {

    @Autowired
    private IPmsUserDao pmsUserDao;

    @Test
    public void addPmsUserTest() {
        String userData = "{\n" +
                "   \"phoneNumber\":\"+8613480773599\",\n" +
                "   \"type\":\"CN\",\n" +
                "   \"encoderCode\":\"1\",\n" +
                "   \"encoderType\":\"E\",\n" +
                "   \"roomName\":\"D102\",\n" +
                "   \"roomName2\":\"\",\n" +
                "   \"roomName3\":\"\",\n" +
                "   \"roomName4\":\"\",\n" +
                "   \"authorisationsGranted\":\"13\",\n" +
                "   \"authorisationsDenied\":\"2\",\n" +
                "   \"startingTime\":\"1230251124\",\n" +
                "   \"endingTime\":\"1430261124\",\n" +
                "   \"reservedUse\":null,\n" +
                "   \"infoTrack1\":\"123\",\n" +
                "   \"infoTrack2\":null,\n" +
                "   \"infoTrack3\":null,\n" +
                "   \"returnCardSerialNumber\":true,\n" +
                "   \"authorisationCode\":\"17jfkdjkf9233989489jfkdjfkjfjkjfkj\"\n" +
                "}";
        Gson gson = new Gson();
        ThirdPmsUser pmsUser = gson.fromJson(userData, ThirdPmsUser.class);
        int insert = pmsUserDao.insert(pmsUser);
        System.out.println("添加成功："+insert);
    }


    @Test
    public void deletePmsUserTest() {
        List<Integer> ids = new ArrayList<>();
        ids.add(13);
        ids.add(15);
        ids.add(20);
        ids.add(27);
        QueryWrapper<ThirdPmsUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("addId", ids);
        int insert = pmsUserDao.delete(queryWrapper);
        System.out.println("删除成功："+insert);
    }


    @Test
    public void updatePmsUserTest() {
        ThirdPmsUser pmsUser = new ThirdPmsUser();
        pmsUser.setAddId(14);
        pmsUser.setPhoneNumber("+8619928717153");
        pmsUser.setType("CN");
        pmsUser.setEncoderCode("1");
        pmsUser.setEncoderType("E");
        pmsUser.setRoomName("D101");
        pmsUser.setRoomName2("D102");
        pmsUser.setRoomName3("D103");
        pmsUser.setAuthorisationsGranted("23");
        pmsUser.setAuthorisationsDenied("1");
        pmsUser.setStartingTime("1230251124");
        pmsUserDao.updateById(pmsUser);
    }
}
