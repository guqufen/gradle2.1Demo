package net.fnsco.service.modules.appuser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.google.common.base.Strings;

import net.fnsco.api.appuser.ConmmService;
import net.fnsco.api.constant.ApiConstant;
import net.fnsco.api.dto.ProtocolDTO;
import net.fnsco.api.dto.VersionDTO;
import net.fnsco.api.dto.VersionResultDTO;
import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.service.dao.master.VersionDao;
import net.fnsco.service.domain.Version;

@Service
public class ConmmServiceImpl extends BaseService implements ConmmService {

    @Autowired
    private VersionDao sysVersionDao;

    @Override
    public ResultDTO checkUpdate(VersionDTO sysVersionDTO) {
        
        String version = sysVersionDTO.getVersion();
        Integer appType = Integer.valueOf(sysVersionDTO.getAppType());
        //非空判断
        if (Strings.isNullOrEmpty(version)) {
            return ResultDTO.fail(ApiConstant.E_APP_PHONE_EMPTY);
        } else if (appType == null) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }

        //客户端版本号
        String[] versionArr = StringUtils.split(version, ".");
        if (versionArr == null || versionArr.length != 3) {
            logger.warn("版本号格式错误,version=" + version);
            return ResultDTO.fail(ApiConstant.E_EDITION_LOGIN);
        }

        String appCode = "sqb";
        List<Version> list = sysVersionDao.selectByPrimaryKey(appCode, appType, version);
        //便利list集合
        int flag = 0;
        Version ver = new Version();
        for (Version temp : list) {
            if (temp.getForceUpdate() == 1) {
                flag = 1;
            }
            ver = temp;
        }
        ver.setForceUpdate(flag);
        return ResultDTO.success(ver);
    }

    @Override
    public VersionResultDTO queryVersionInfo(VersionDTO sysVersionDTO) {
        List<Version> list = sysVersionDao.selectByPrimaryKey(sysVersionDTO.getAppCode(), sysVersionDTO.getAppType(), sysVersionDTO.getVersion());
        //便利list集合
        int flag = 0;
        Version ver = new Version();
        for (Version temp : list) {
            if (temp.getForceUpdate() == 1) {
                flag = 1;
            }
            ver = temp;
        }

        ver.setForceUpdate(flag);
        VersionResultDTO result = new VersionResultDTO();
        result.setDownloadUrl1(ver.getDownloadUrl1());
        result.setForceUpdate(String.valueOf(flag));
        Boolean isUpdate = false;
        if (sysVersionDTO.getVersion().equals(ver.getVersion())) {
            isUpdate = false;
        } else {
            isUpdate = true;
        }
        result.setIsUpdate(isUpdate);
        result.setVersion(ver.getVersion());
        return result;
    }

    //获取用户协议
    @Override
    public ResultDTO getProtocol(ProtocolDTO protocolDTO) {
        Map<String,String> map=new HashMap<>();
        try{
            if (protocolDTO.getProtocol() == 1) {
                map.put("title", "认领说明");
                StringBuilder sb = new StringBuilder();
                sb.append("        浙付通App是法奈昇科技有限公司自主研发的手机端软件.针对自有POS机商户可输入开户登记手机号码进行认领操作登录。").append(FileUtils.separator);
                sb.append("        如需开通二维码支付，请拨打400-1818-823联系客服，提交相关认证资料，认证通过后即可认领APP账号，开启二维码支付功能。").append(FileUtils.separator);
                sb.append("        感谢您的使用，祝您生活愉快，生意兴隆！ ").append(FileUtils.separator);
                map.put("content", sb.toString());
            }else if(protocolDTO.getProtocol()==2){
                map.put("title", "用户服务协议须知");
                StringBuilder sb = new StringBuilder();
                sb.append("请务必认真阅读和理解本《用户服务协议》（以下简称《协议》）中规定的所有权利和限制。除非您接受本《协议》条款，否则您无权认领、登录或使用本协议所涉及的相关服务。您一旦认领、登录、使用或以任何方式使用本《协议》所涉及的相关服务的行为将视为对本《协议》的接受，即表示您同意接受本《协议》各项条款的约束。如果您不同意本《协议》中的条款，请不要认领、登录或使用本《协议》相关服务。 本《协议》是用户与法奈昇网络科技有限公司之间的法律协议。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第一章  服务内容").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第一条  法奈昇网络科技有限公司是互联网技术与POS机管理、POSP系统管理融合的信息管理平台，主要内容包括账户查询功能、交易流水查询、POS账户管理等功能服务。").append(FileUtils.separator);
                sb.append("第二条  您一旦认领成功成为用户，您将得到一个密码和帐号，您需要对自己在帐户中的所有活动和事件负全责。如果由于您的过失导致您的帐号和密码脱离您的控制，则由此导致的针对您、或任何第三方造成的损害，您将承担全部责任。").append(FileUtils.separator);
                sb.append("第三条  用户理解并接受，法奈昇网络科技有限公司仅提供相关的网络服务，除此之外与相关网络服务有关的设备（如个人电脑、手机、及其他与接入互联网或移动互联网有关的装置）及所需的费用（如为接入互联网而支付的电话费及上网费、为使用移动网而支付的手机费）均应由用户自行负担。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第二章  用户使用规则").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第四条 用户在申请使用法奈昇网络科技有限公司网络服务时，必须向法奈昇网络科技有限公司提供准确的个人资料，如个人资料有任何变动，必须及时更新。因用户提供个人资料不准确、不真实而引发的一切后果由用户承担。").append(FileUtils.separator);
                sb.append("第五条 用户应妥善保存帐号、密码，避免以任何脱离用户控制的形式交由他人使用。如用户发现其帐号遭他人非法使用，应立即通知法奈昇网络科技有限公司。因黑客行为或用户的保管疏忽导致帐号、密码遭他人非法使用，法奈昇网络科技有限公司不承担任何责任。").append(FileUtils.separator);
                sb.append("第六条 用户应当为自身认领帐户下的一切行为负责，因用户行为而导致的用户自身或其他任何第三方的任何损失或损害，法奈昇网络科技有限公司不承担责任。").append(FileUtils.separator);
                sb.append("第七条 用户理解并接受法奈昇网络科技有限公司提供的服务中可能包括管理建议、广告，用户同意在使用过程中显示法奈昇网络科技有限公司和第三方供应商、合作伙伴提供的广告。").append(FileUtils.separator);
                sb.append("第八条 用户在使用法奈昇网络科技有限公司网络服务过程中，必须遵循以下原则：").append(FileUtils.separator);
                sb.append("1、遵守中国有关的法律和法规；").append(FileUtils.separator);
                sb.append("2、遵守所有与网络服务有关的网络协议、规定和程序；").append(FileUtils.separator);
                sb.append("3、不得为任何非法目的而使用网络服务系统；").append(FileUtils.separator);
                sb.append("4、不得利用法奈昇网络科技有限公司网络服务系统进行任何可能对互联网或移动网正常运转造成不利影响的行为；").append(FileUtils.separator);
                sb.append("5、不得利用法奈昇网络科技有限公司提供的网络服务上传、展示或传播任何虚假的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、庸俗淫秽的或其他任何非法的信息资料；").append(FileUtils.separator);
                sb.append("6、不得侵犯法奈昇网络科技有限公司和其他任何第三方的专利权、著作权、商标权、名誉权或其他任何合法权益；").append(FileUtils.separator);
                sb.append("7、不得利用法奈昇网络科技有限公司网络服务系统进行任何不利于法奈昇网络科技有限公司的行为；").append(FileUtils.separator);
                sb.append("8、如发现任何非法使用用户帐号或帐号出现安全漏洞的情况，应立即通告法奈昇网络科技有限公司。").append(FileUtils.separator);
                sb.append("第九条 如用户在使用网络服务时违反任何上述规定，法奈昇网络科技有限公司或其授权的人有权要求用户改正或直接采取一切必要的措施（包括但不限于更改或删除用户账号等、暂停或终止用户使用网络服务的权利）以减轻用户不当行为造成的影响。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第三章  服务变更、中断或终止").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第十条 鉴于网络服务的特殊性，用户同意法奈昇网络科技有限公司有权根据业务发展情况随时变更、中断或终止部分或全部的网络服务而无需通知用户，也无需对任何用户或任何第三方承担任何责任；").append(FileUtils.separator);
                sb.append("第十一条 用户理解，法奈昇网络科技有限公司需要定期或不定期地对提供网络服务的平台（如互联网网站、移动网络等）或相关的设备进行检修或者维护，如因此类情况而造成网络服务在合理时间内的中断，法奈昇网络科技有限公司无需为此承担任何责任，但法奈昇网络科技有限公司应尽可能事先进行通告。").append(FileUtils.separator);
                sb.append("第十二条 如发生下列任何一种情形，法奈昇网络科技有限公司有权随时中断或终止向用户提供本《协议》项下的网络服务（包括收费网络服务）而无需对用户或任何第三方承担任何责任：").append(FileUtils.separator);
                sb.append("1、用户提供的个人资料不真实；").append(FileUtils.separator);
                sb.append("2、用户违反本《协议》中规定的使用规则。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第四章  知识产权").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第十三条 提供的网络服务中包含的任何文本、图片、图形、音频和/或视频资料均受版权、商标和/或其它财产所有权法律的保护，未经相关权利人同意，上述资料均不得用于任何商业目的。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第五章  隐私保护").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第十四条 保护用户隐私是法奈昇网络科技有限公司的一项基本政策，法奈昇网络科技有限公司保证不对外公开或向第三方提供单个用户的认领资料及用户在使用网络服务时存储在法奈昇网络科技有限公司的非公开内容，但下列情况除外：").append(FileUtils.separator);
                sb.append("1、事先获得用户的明确授权；").append(FileUtils.separator);
                sb.append("2、根据有关的法律法规要求；").append(FileUtils.separator);
                sb.append("3、按照相关政府主管部门的要求；").append(FileUtils.separator);
                sb.append("4、为维护社会公众的利益；").append(FileUtils.separator);
                sb.append("5、为维护法奈昇网络科技有限公司的合法权益。").append(FileUtils.separator);
                sb.append("第十五条 法奈昇网络科技有限公司可能会与第三方合作向用户提供相关的网络服务，在此情况下，如该第三方同意承担与法奈昇网络科技有限公司同等的保护用户隐私的责任，则法奈昇网络科技有限公司有权将用户的认领资料等提供给该第三方。").append(FileUtils.separator);
                sb.append("第十六条 在不透露单个用户隐私资料的前提下，法奈昇网络科技有限公司有权对整个用户数据库进行分析并对用户数据库进行商业上的利用。").append(FileUtils.separator);
                sb.append("第十七条 法奈昇网络科技有限公司制定了以下四项隐私权保护原则，指导我们如何来处理产品中涉及到用户隐私权和用户信息等方面的问题：").append(FileUtils.separator);
                sb.append("1、利用我们收集的信息为用户提供有价值的产品和服务。").append(FileUtils.separator);
                sb.append("2、开发符合隐私权标准和隐私权惯例的产品。").append(FileUtils.separator);
                sb.append("3、将个人信息的收集透明化，并由权威第三方监督。").append(FileUtils.separator);
                sb.append("4、尽最大的努力保护我们掌握的信息。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第六章  免责声明").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第十八条 法奈昇网络科技有限公司不担保网络服务一定能满足用户的要求，也不担保网络服务不会中断，对网络服务的及时性、安全性、准确性也都不作担保。").append(FileUtils.separator);
                sb.append("第十九条 法奈昇网络科技有限公司不保证为向用户提供便利而设置的外部链接的准确性和完整性，同时，对于该等外部链接指向的不由法奈昇网络科技有限公司实际控制的任何网页上的内容，法奈昇网络科技有限公司不承担任何责任。").append(FileUtils.separator);
                sb.append("第二十条 对于因电信系统或互联网网络故障、计算机故障或病毒、信息损坏或丢失、计算机系统问题或其它任何不可抗力原因而产生损失，法奈昇网络科技有限公司不承担任何责任，但将尽力减少因此而给用户造成的损失和影响。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第七章 法律及争议解决").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第二十一条 本协议适用中华人民共和国法律。").append(FileUtils.separator);
                sb.append("第二十二条 因本协议引起的或与本协议有关的任何争议，各方应友好协商解决；协商不成的，任何一方均可将有关争议提交至北京仲裁委员会并按照其届时有效的仲裁规则仲裁；仲裁裁决是终局的，对各方均有约束力。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第八章  其他条款").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("第二十三条 如果本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，或违反任何适用的法律，则该条款被视为删除，但本协议的其余条款仍应有效并且有约束力。").append(FileUtils.separator);
                sb.append("第二十四条 法奈昇网络科技有限公司有权随时根据有关法律、法规的变化以及公司经营状况和经营策略的调整等修改本协议，而无需另行单独通知用户。用户可随时通过网络浏览最新服务协议条款。当发生有关争议时，以最新的协议文本为准。如果不同意法奈昇网络科技有限公司对本协议相关条款所做的修改，用户有权停止使用网络服务。如果用户继续使用网络服务，则视为用户接受法奈昇网络科技有限公司对本协议相关条款所做的修改。").append(FileUtils.separator);
                sb.append("第二十五条 法奈昇网络科技有限公司在法律允许最大范围对本协议拥有解释权与修改权。").append(FileUtils.separator);
                map.put("content", sb.toString());
            }
            return ResultDTO.success(map);
        }catch(Exception e){
            logger.error("获取协议异常",e);
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }
      
    }

}
