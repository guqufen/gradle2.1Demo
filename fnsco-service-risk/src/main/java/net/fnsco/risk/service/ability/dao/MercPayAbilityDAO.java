package net.fnsco.risk.service.ability.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import net.fnsco.risk.service.ability.entity.MercPayAbilityDO;
import net.fnsco.risk.service.ability.dao.helper.MercPayAbilityProvider;

import java.util.List;;

public interface MercPayAbilityDAO {

    @Results({@Result( column = "entity_inner_code",property = "entityInnerCode"),@Result( column = "pay_ability",property = "payAbility") })
    @Select("SELECT * FROM risk_merc_pay_ability WHERE id = #{id}")
    public MercPayAbilityDO getById(@Param("id") int id);

    @Insert("INSERT into risk_merc_pay_ability(id,entity_inner_code,month,pay_ability) VALUES (#{id},#{entityInnerCode},#{month},#{payAbility})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insert(MercPayAbilityDO mercPayAbility);

    @Delete("DELETE FROM risk_merc_pay_ability WHERE id = #{id}")
    public int deleteById(@Param("id") int id);

    @UpdateProvider(type = MercPayAbilityProvider.class, method = "update")
    public int update(@Param("mercPayAbility") MercPayAbilityDO  mercPayAbility);

    @Results({@Result( column = "entity_inner_code",property = "entityInnerCode"),@Result( column = "pay_ability",property = "payAbility") })
    @SelectProvider(type = MercPayAbilityProvider.class, method = "pageList")
    public List<MercPayAbilityDO> pageList(@Param("mercPayAbility") MercPayAbilityDO mercPayAbility, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @SelectProvider(type = MercPayAbilityProvider.class, method = "pageListCount")
    public Integer pageListCount(@Param("mercPayAbility") MercPayAbilityDO mercPayAbility);
    
    /**
     * 更新
     * @param mercPayAbility
     * @return
     */
    @UpdateProvider(type = MercPayAbilityProvider.class, method = "updateByPayAbility")
    public int updateByPayAbility(@Param("mercPayAbility") MercPayAbilityDO  mercPayAbility);

}