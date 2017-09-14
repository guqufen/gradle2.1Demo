package net.fnsco.bigdata.service.merchant.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import net.fnsco.bigdata.service.merchant.dao.helper.MerchantPosProvider;
import net.fnsco.bigdata.service.merchant.entity.MerchantPosDO;;

public interface MerchantPosSimpleDao {

    @Results({ @Result(column = "inner_code", property = "innerCode"), @Result(column = "channel_id", property = "channelId"), @Result(column = "pos_name", property = "posName"),
               @Result(column = "sn_code", property = "snCode"), @Result(column = "pos_factory", property = "posFactory"), @Result(column = "pos_type", property = "posType"),
               @Result(column = "bank_id", property = "bankId"), @Result(column = "merc_refer_name", property = "mercReferName"), @Result(column = "pos_addr", property = "posAddr"),
               @Result(column = "pos_province", property = "posProvince"), @Result(column = "pos_city", property = "posCity"), @Result(column = "pos_area", property = "posArea") })
    @Select("SELECT * FROM m_merchant_pos WHERE sn_code = #{snCode} limit 1")
    public MerchantPosDO selectBySnCode(@Param("snCode") String snCode);

    @Results({ @Result(column = "inner_code", property = "innerCode"), @Result(column = "channel_id", property = "channelId"), @Result(column = "pos_name", property = "posName"),
               @Result(column = "sn_code", property = "snCode"), @Result(column = "pos_factory", property = "posFactory"), @Result(column = "pos_type", property = "posType"),
               @Result(column = "bank_id", property = "bankId"), @Result(column = "merc_refer_name", property = "mercReferName"), @Result(column = "pos_addr", property = "posAddr"),
               @Result(column = "pos_province", property = "posProvince"), @Result(column = "pos_city", property = "posCity"), @Result(column = "pos_area", property = "posArea") })
    @Select("SELECT * FROM m_merchant_pos pos WHERE sn_code = #{snCode} and exists (select 1 from m_merchant_channel channel where channel.channel_type='00' and channel.id=pos.channel_id)")
    public List<MerchantPosDO> selectBySnCodeChannelType(@Param("snCode") String snCode);

    @Results({ @Result(column = "inner_code", property = "innerCode"), @Result(column = "channel_id", property = "channelId"), @Result(column = "pos_name", property = "posName"),
               @Result(column = "sn_code", property = "snCode"), @Result(column = "pos_factory", property = "posFactory"), @Result(column = "pos_type", property = "posType"),
               @Result(column = "bank_id", property = "bankId"), @Result(column = "merc_refer_name", property = "mercReferName"), @Result(column = "pos_addr", property = "posAddr"),
               @Result(column = "pos_province", property = "posProvince"), @Result(column = "pos_city", property = "posCity"), @Result(column = "pos_area", property = "posArea") })
    @Select("SELECT * FROM m_merchant_pos WHERE id = #{id}")
    public MerchantPosDO getById(@Param("id") int id);

    @Insert("INSERT into m_merchant_pos(id,inner_code,channel_id,pos_name,sn_code,pos_factory,pos_type,status,bank_id,merc_refer_name,pos_addr,pos_province,pos_city,pos_area) VALUES (#{id},#{innerCode},#{channelId},#{posName},#{snCode},#{posFactory},#{posType},#{status},#{bankId},#{mercReferName},#{posAddr},#{posProvince},#{posCity},#{posArea})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(MerchantPosDO merchantPos);

    @Delete("DELETE FROM m_merchant_pos WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = MerchantPosProvider.class, method = "update")
    public int update(@Param("merchantPos") MerchantPosDO merchantPos);

    @Update("UPDATE m_merchant_pos set pos_name=#{posName} where sn_code=#{snCode} ")
    public int updateBySnCode(@Param("snCode") String snCode, @Param("posName") String posName);

    @Results({ @Result(column = "inner_code", property = "innerCode"), @Result(column = "channel_id", property = "channelId"), @Result(column = "pos_name", property = "posName"),
               @Result(column = "sn_code", property = "snCode"), @Result(column = "pos_factory", property = "posFactory"), @Result(column = "pos_type", property = "posType"),
               @Result(column = "bank_id", property = "bankId"), @Result(column = "merc_refer_name", property = "mercReferName"), @Result(column = "pos_addr", property = "posAddr"),
               @Result(column = "pos_province", property = "posProvince"), @Result(column = "pos_city", property = "posCity"), @Result(column = "pos_area", property = "posArea") })
    @SelectProvider(type = MerchantPosProvider.class, method = "pageList")
    public List<MerchantPosDO> pageList(@Param("merchantPos") MerchantPosDO merchantPos, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = MerchantPosProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("merchantPos") MerchantPosDO merchantPos);

}