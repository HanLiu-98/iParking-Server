package xyz.hanliu.iparking.test;

import org.junit.Test;
import xyz.hanliu.iparking.dao.ParkingSpaceDao;
import xyz.hanliu.iparking.domain.ParkingSpace;
import xyz.hanliu.iparking.domain.ParkingSpace_General;
import xyz.hanliu.iparking.util.Dateutils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author HanLiu
 * @create 2020-03-11-18:31
 * @blogip 47.110.70.206
 */
public class ParkingSpaceDaoTest {


    @Test
    public void testAddSpace() throws ParseException {
        String area = "安徽省合肥市蜀山区";
        String positionDetail = "桃花大道303号";
        Date starTtime = Dateutils.strToDate(" 2020-03-10 16:30");
        Date endTime = Dateutils.strToDate(" 2020-03-10 16:30");
        ;
        float price = 56;
        String remark = "只能停放小轿车";
        String imgPath = "123.jpg";
        int status = 0;
        String ownerMobile = "17355216002";

        ParkingSpace space = new ParkingSpace();

        ParkingSpaceDao dao = new ParkingSpaceDao();
        int count = dao.addSpace(space);
        System.out.println(count);


    }


    @Test
    public void testFindForId() throws ParseException {
        String area = "安徽省合肥市蜀山区";
        String positionDetail = "桃花大道303号";
        Date starTtime = Dateutils.strToDate(" 2020-03-10 16:30");
        Date endTime = Dateutils.strToDate(" 2020-03-10 16:30");
        ;
        float price = 56;
        String remark = "只能停放小轿车";
        String imgPath = "123.jpg";
        int status = 0;
        String ownerMobile = "17355216002";

        ParkingSpace space = new ParkingSpace();


        ParkingSpaceDao dao = new ParkingSpaceDao();
        int count = dao.findForId(space);
        System.out.println(count);
    }


    @Test
    public void testFindById() {
        ParkingSpaceDao dao = new ParkingSpaceDao();
        ParkingSpace space = dao.findById(31);
        System.out.println(space.toString());
    }

    @Test
    public void testFindByArea() {
        ParkingSpaceDao dao = new ParkingSpaceDao();
        List<ParkingSpace_General> list = dao.findByArea("北京市北京市东城区");
        for (ParkingSpace_General ps_g : list) {
            System.out.println(ps_g);

        }

    }
}
