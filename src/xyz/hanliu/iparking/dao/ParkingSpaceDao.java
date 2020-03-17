package xyz.hanliu.iparking.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.hanliu.iparking.domain.ParkingSpace;
import xyz.hanliu.iparking.domain.ParkingSpace_General;
import xyz.hanliu.iparking.util.JDBCUtils;
import java.util.List;


/**
 * @author HanLiu
 * @create 2020-03-10-16:08
 * @blogip 47.110.70.206
 */


/**
 * 操作数据库中ParkingSpace表的类
 */
public class ParkingSpaceDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 添加车位(insert)
     * @param space 包含车位全部数据
     * @return count 在数据库中成功操作数据的条数
     */
    public int addSpace(ParkingSpace space) {
        try {
            String sql = "insert into parkingspace values (?,?,?,?,?,?,?,?,?,?)";
            int count = template.update(sql, space.getId(), space.getArea(),
                    space.getPositionDetail(), space.getStartTime(),
                    space.getEndTime(), space.getPrice(),
                    space.getRemark(), space.getImagePath(), space.getOwnerMobile(),
                    space.getStatus());
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据车位详细信息查找车位的Id
     * @param space 包含车位全部数据（除了id）
     * @return result 车位id
     */
    public Integer findForId(ParkingSpace space) {
        try {
            String sql = "select id from parkingspace where area=? and positiondetail=?" +
                    " and starttime=? and " +
                    "endtime =? and price=? and remark=? and imagepath=? and ownermobile=? " +
                    "and status=?";
            //调用queryForObject方法，想要查出来的书是整数，返回值用int的封装类Integer
            Integer result = template.queryForObject(sql,
                    Integer.class,
                    space.getArea(), space.getPositionDetail(), space.getStartTime(),
                    space.getEndTime(), space.getPrice(), space.getRemark(),
                    space.getImagePath(), space.getOwnerMobile(),
                    space.getStatus());
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据车位的id查找详细信息
     * @param id 车位id
     * @return space 车位详细数据
     */
    public ParkingSpace findById(int id) {
        try {
            String sql = "select * from parkingspace where id=?";
            //调用queryForObject方法,直接返回对象
            ParkingSpace space = template.queryForObject(sql,
                    new BeanPropertyRowMapper<ParkingSpace>(ParkingSpace.class),
                    id);
            return space;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据车位的所在区域查找大体信息
     * @param area 车位区域
     * @return List<ParkingSpace_General> 车位大体数据列表信息
     */
    public List<ParkingSpace_General> findByArea(String area) {
        //查找出来的是未过期的可用车位
        String sql = "select id,positiondetail,starttime,endtime,price,imagepath " +
                "from parkingspace where area=? and status=0 and starttime>now() ";
        //将查询结果直接封装成List
        List<ParkingSpace_General> list = template.query(sql,
                new BeanPropertyRowMapper<ParkingSpace_General>(ParkingSpace_General.class), area);
        return list;

    }


}
