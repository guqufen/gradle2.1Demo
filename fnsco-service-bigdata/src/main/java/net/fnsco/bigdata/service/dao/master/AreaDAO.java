package net.fnsco.bigdata.service.dao.master;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import net.fnsco.bigdata.service.domain.Area;

public interface AreaDAO {

	public Area getById(@Param("id") int id);

	public void insert(Area area);

	public int deleteById(@Param("id") int id);

	public int update(@Param("area") Area area);

//	public List<Area> pageList(@Param("area") Area area, @Param("pageNum") Integer pageNum,
//			@Param("pageSize") Integer pageSize);
//
//	public Integer pageListCount(@Param("area") Area area);

	public List<Area> getProvinceList();

	public List<Area> getListBySupperId(int supperId);

	public List<Area> getList();

	public String getNameById(int id);
}
