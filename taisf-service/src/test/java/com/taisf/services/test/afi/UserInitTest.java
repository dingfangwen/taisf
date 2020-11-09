package com.taisf.services.test.afi;

import com.jk.framework.base.head.Header;
import com.jk.framework.base.utils.JsonEntityTransform;
import com.jk.framework.base.utils.MD5Util;
import com.jk.framework.base.utils.UUIDGenerator;
import com.taisf.services.base.dao.UpgradeDao;
import com.taisf.services.base.entity.UpgradeEntity;
import com.taisf.services.message.api.MessageInfoService;
import com.taisf.services.test.common.BaseTest;
import com.taisf.services.user.dao.UserDao;
import com.taisf.services.user.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

/**
 * <p>TODO</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2019/6/24.
 * @version 1.0
 * @since 1.0
 */
public class UserInitTest extends BaseTest {


    @Resource(name = "user.userDao")
    private UserDao userDao;


    public static void main(String[] args) {


        System.out.println(MD5Util.MD5Encode("13023175708"));
    }

    /**
     * 功能：Java读取txt文件的内容 步骤：1：先获得文件句柄 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流 4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
     *
     * @param filePath
     *            文件路径[到达文件:如： D:\aa.txt]
     * @return 将这个文件按照每一行切割成数组存放到list中。
     */
    public static List<String> readTxtFileIntoStringArrList(String filePath)
    {
        List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return list;
    }

    @Test
    public void ccc() {
        List<String> stringList = readTxtFileIntoStringArrList("/Users/fangwending/user-11071.txt");

        System.out.println("-------使用BufferedReader读取-----------");
        for(String str : stringList){
            System.out.println(str);
        }



        System.out.println("开始打印sql-------");
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------");








        for(String str : stringList){
//            StringBuffer sbName = new StringBuffer();
//            StringBuffer sbTel = new StringBuffer();

//            boolean name = true;

            String sbName = "";
            String sbTel = "";
            String userCode = "";


            String[] chars = str.split("-");

            for (String aChar : chars) {
                if ("".equals(sbName)){
                    sbName = aChar;
                }else if ("".equals(sbTel)){
                    sbTel = aChar;
                }else if ("".equals(userCode)){
                    userCode = aChar;
                }else {
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                }
            }



            String sql = "update t_user set user_password = 'userPassword'  where user_phone = 'userPhone'   and update_time > '2019-11-07 110:57:36' and user_type = 1  ;";
            String phone = sbTel.trim().toString();

            sql = sql.replace("userPhone",phone);
            sql = sql.replace("userPassword",MD5Util.MD5Encode(phone));



            System.out.println(sql);
        }
    }




    @Test
    public void aa() {
        List<String> stringList = readTxtFileIntoStringArrList("/Users/fangwending/user-11071.txt");

        System.out.println("-------使用BufferedReader读取-----------");
        for(String str : stringList){
            System.out.println(str);
        }



        System.out.println("开始打印sql-------");
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------");







        String pre = "INSERT INTO `t_user` (`user_uid`, `user_name`, `user_phone`, `user_code`, `biz_code`, `enterprise_code`, `enterprise_name`, `user_password`, `user_status`, `user_role`, `product_source`, `user_type`, `user_business_status`, `is_admin`, `is_pwd`, `pay_code`, `create_time`, `update_time`, `qr_code`)\n" +
                "VALUES ";

        System.out.println(pre);



        for(String str : stringList){
//            StringBuffer sbName = new StringBuffer();
//            StringBuffer sbTel = new StringBuffer();

//            boolean name = true;

            String sbName = "";
            String sbTel = "";
            String userCode = "";


            String[] chars = str.split("-");

            for (String aChar : chars) {
                if ("".equals(sbName)){
                    sbName = aChar;
                }else if ("".equals(sbTel)){
                    sbTel = aChar;
                }else if ("".equals(userCode)){
                    userCode = aChar;
                }else {
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                    System.out.println("===============");
                }
            }

            String uuid = UUIDGenerator.hexUUID();
            String pwd = MD5Util.MD5Encode(sbTel.toString());

            String bizCode = "lfgyl";
            String enterpriseCode = "lfgyl";


            String init = "('userUid', 'userName', 'userPhone', 'userCode', 'bizCode', 'enterpriseCode', '海沃氏餐厅', 'userPassword', 2, 1, 1, 1, 1, 0, 0, NULL, now(),  now(), NULL),";

            init = init.replace("userUid",uuid);
            init = init.replace("userName",sbName.toString());
            init = init.replace("userPhone",sbTel.toString());
            init = init.replace("userCode",userCode);
            init = init.replace("userPassword",pwd);
            init = init.replace("bizCode",bizCode);
            init = init.replace("enterpriseCode",enterpriseCode);
            System.out.println(init);
        }
    }



    @Test
    public void bb() {
        System.out.println(MD5Util.MD5Encode("cd"));
    }




    @Test
    public void testAddEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserPhone("23164324796");
        userEntity.setUserPassword("fdasfdsafdas");
        userEntity.setUserStatus(2);
        userEntity.setUserType(1);
        userEntity.setUserBusinessStatus(1);
        userEntity.setCreateTime(new Date());
        userEntity.setUpdateTime(new Date());
        int aa=  userDao.add(userEntity);
        System.out.println(aa);
    }





}
