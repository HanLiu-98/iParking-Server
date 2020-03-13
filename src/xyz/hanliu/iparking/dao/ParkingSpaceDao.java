package xyz.hanliu.iparking.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import xyz.hanliu.iparking.domain.ParkingSpace;
import xyz.hanliu.iparking.domain.ParkingSpace_General;
import xyz.hanliu.iparking.domain.User;
import xyz.hanliu.iparking.util.JDBCUtils;

import java.util.List;

/**
 * @author HanLiu
 * @create 2020-03-10-16:08
 * @blogip 47.110.70.206
 */
public class ParkingSpaceDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 添加车位
     *
     * @param space 包含车位全部数据
     * @return count 在数据库中成功操作数据的条数
     */
    public int addSpace(ParkingSpace space) {
        try {
            String sql = "insert into parkingspace values (?,?,?,?,?,?,?,?,?,?)";
            int count = template.update(sql, space.getId(), space.getArea(), space.getPositionDetail(), space.getStartTime(),
                    space.getEndTime(), space.getPrice(), space.getRemark(), space.getImagePath(), space.getOwnerMobile(),
                    space.getStatus());
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 查找车位的Id
     *
     * @param space 包含车位全部数据（除了id）
     * @return result 车位id
     */
    public Integer findForId(ParkingSpace space) {
        try {
            //1.编写sql
            String sql = "select id from parkingspace where area=? and positiondetail=?" +
                    " and starttime=? and " +
                    "endtime =? and price=? and remark=? and imagepath=? and ownermobile=? and status=?";
            //2.调用query方法
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
     *
     * @param id 车位id
     * @return space 车位详细数据
     */
    public ParkingSpace findById(int id) {
        try {
            //1.编写sql
            String sql = "select * from parkingspace where id=?";
            //2.调用query方法
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
     *
     * @param area 车位区域
     * @return space 车位大体数据
     */
    public List<ParkingSpace_General> findByArea(String area) {
        String sql = "select id,positiondetail,starttime,endtime,price,imagepath from parkingspace where area=?";
        List<ParkingSpace_General> list = template.query(sql, new BeanPropertyRowMapper<ParkingSpace_General>(ParkingSpace_General.class), area);
        return list;

    }


}
