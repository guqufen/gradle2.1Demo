package net.fnsco.order.service.modules.appuser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.core.base.BaseService;
import net.fnsco.core.base.ResultDTO;
import net.fnsco.core.utils.FileUtils;
import net.fnsco.order.api.appuser.ConmmService;
import net.fnsco.order.api.constant.ApiConstant;
import net.fnsco.order.api.constant.ConstantEnum;
import net.fnsco.order.api.dto.ProtocolDTO;
import net.fnsco.order.api.dto.SuggestDTO;
import net.fnsco.order.api.dto.VersionDTO;
import net.fnsco.order.api.dto.VersionResultDTO;
import net.fnsco.order.service.dao.master.AppUserDao;
import net.fnsco.order.service.dao.master.SysBankDao;
import net.fnsco.order.service.dao.master.SysSuggestDao;
import net.fnsco.order.service.dao.master.VersionDao;
import net.fnsco.order.service.domain.AppUser;
import net.fnsco.order.service.domain.SysSuggest;
import net.fnsco.order.service.domain.Version;

@Service
public class ConmmServiceImpl extends BaseService implements ConmmService {

    @Autowired
    private VersionDao sysVersionDao;
    @Autowired
    private SysSuggestDao sysSuggestDao;
    @Autowired
    private AppUserDao appUserDao;
    
    private ResultDTO validateVersion(VersionDTO sysVersionDTO) {
        String version = sysVersionDTO.getVersion();
        Integer appType = Integer.valueOf(sysVersionDTO.getAppType());
        String appCode = sysVersionDTO.getAppCode();
        //非空判断
        if (Strings.isNullOrEmpty(version)) {
            return ResultDTO.fail(ApiConstant.E_VERSION_NULL);
        }
        if (appType == null) {
            return ResultDTO.fail(ApiConstant.E_APP_PASSWORD_EMPTY);
        }
        if (Strings.isNullOrEmpty(appCode)) {
            return ResultDTO.fail(ApiConstant.E_APPCODE_NULL);
        }
        //客户端版本号并转换为int
        String[] versionArr = StringUtils.split(version, ".");
        if (versionArr.length != 3) {
            logger.error("版本号格式错误,version=" + version);
            return ResultDTO.fail(ApiConstant.E_EDITION_ERROR);
        }
        return ResultDTO.success();
    }

    @Override
    public ResultDTO queryLastVersionInfo(VersionDTO sysVersionDTO) {
        ResultDTO valResult = validateVersion(sysVersionDTO);
        if (!valResult.isSuccess()) {
            return valResult;
        }
        List<Version> list = sysVersionDao.selectByPrimaryKey(sysVersionDTO.getAppCode(), sysVersionDTO.getAppType(), sysVersionDTO.getVersion());
        int flag = 0;
        Version ver = new Version();
        for (Version temp : list) {
            if (temp.getForceUpdate() == 1) {
                flag = 1;
            }
            ver = temp;
        }

        VersionResultDTO result = new VersionResultDTO();
        result.setDownloadUrl1(ver.getDownloadUrl1());
        result.setDownloadUrl2(ver.getDownloadUrl2());
        result.setForceUpdate(String.valueOf(flag));
        Boolean isUpdate = false;
        if(null != ver.getVersion()){
            int lastVersion = Integer.valueOf(ver.getVersion().replaceAll("\\.", ""));
            int curressVersion = Integer.valueOf(sysVersionDTO.getVersion().replaceAll("\\.", ""));
            if (curressVersion < lastVersion) {
                isUpdate = true;
            } else {
                isUpdate = false;
            }
        }
        result.setIsUpdate(isUpdate);
        result.setVersion(ver.getVersion());
        result.setRemark(ver.getRemark());
        result.setVersionDesc(ver.getVersionDesc());
        return ResultDTO.success(result);
    }

    //获取用户协议
    @Override
    public ResultDTO getProtocol(ProtocolDTO protocolDTO) {
        Map<String, String> map = new HashMap<>();
        try {
            if (protocolDTO.getProtocol() == 1) {
                map.put("title", "用户服务协议须知");
                StringBuilder sb = new StringBuilder();
                sb.append("").append(FileUtils.separator);
                sb.append("一、提示条款").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("欢迎您使用数钱吧平台（详见定义条款）为您提供的服务。数钱吧是一款商户移动端数据管理平台。主要内容包括查看交易流水、数据统计、经营分析、增值服务等网络服务").append(FileUtils.separator);
                sb.append("为了保障您的权益，请在使用数钱吧平台服务（详见定义条款）前，详细阅读并理解本协议（详见定义条款）的所有内容，特别是免除或者限制责任的条款、争议解决和法律适用条款以及开通或使用某项服务的单独协议，及选择接受或不接受的部分。一旦您采取以合理的理解表明您希望与数钱吧平台签订本协议的行为（例如，点击按钮上书写“同意《数钱吧平台注册协议》”或类似文字，且页面上同时列明了本协议的内容或者可以有效展示本协议内容的链接；或您下载、安装、获取/激活、登录含服务内容的数钱吧平台并使用服务），即视为您已充分阅读、理解并接受本协议的全部内容，并与数钱吧平台达成协议。如您对本协议有任何疑问的，应向数钱吧平台客服咨询。如果您不同意本协议的约定，您应立即停止使用数钱吧平台服务。").append(FileUtils.separator);
                sb.append("您与数钱吧平台达成协议后，您承诺接受并遵守本协议的约定，并不得以未阅读本协议的内容或者未获得平台对您问询的解答等理由，主张本协议无效，或要求撤销本协议。在本协议履行过程中，数钱吧平台可以依其单独判断暂时停止提供、限制或改变平台服务。一旦本协议的内容发生变动，数钱吧平台将通过平台公布最新的服务协议，不再向您作个别通知。如果您不同意数钱吧平台对本服务协议所做的修改，您有权停止使用平台服务。如果您继续使用数钱吧平台服务，则视为您接受数钱吧平台对本协议所做的修改，并应遵照修改后的协议执行。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("二、定义").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("2.1数钱吧平台：包括但不限于网页和移动端APP等，用户注册或使用该客户端时，则数钱吧平台仅指相应的网页和客户端。").append(FileUtils.separator);
                sb.append("2.2数钱吧：数钱吧平台运营者，包括但不限于杭州法奈昇科技有限公司。").append(FileUtils.separator);
                sb.append("2.3数钱吧平台服务：数钱吧基于互联网，以包含数钱吧平台网站、移动客户端等在内的各种形态（包括未来技术发展出现的新的服务形态）向您提供的各项服务。").append(FileUtils.separator);
                sb.append("2.4数钱吧注册协议：本协议内容包括协议正文、法律声明、平台规则及所有平台已经发布或将来可能发布的各类规则、公告或通知。").append(FileUtils.separator);
                sb.append("2.5数钱吧平台规则：包括在数钱吧平台已经发布及后续发布的全部规则、解读、公告等内容以及数钱吧在论坛、帮助中心内发布的各类规则、实施细则、流程说明等。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("三、账户注册及使用").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("3.1您确认，在您开始注册程序使用数钱吧平台服务前，您应当具备中华人民共和国法律规定的与您行为相适应的民事行为能力。若您不具备前述与您行为相适应的民事行为能力，则您及您的监护人应依照法律规定承担因此而导致的一切后果。").append(FileUtils.separator);
                sb.append("3.2您设置的用户名不得违反国家法律法规及数钱吧平台规则关于用户名的管理规定，否则数钱吧可回收您的用户名。用户名的回收不影响您以手机号码登录数钱吧平台并使用数钱吧平台服务。").append(FileUtils.separator);
                sb.append("3.3您的注册账户为您自行设置并由您保管，数钱吧任何时候均不会主动要求您提供您的账户密码。因此，建议您务必保管好您的账户，并确保您在每个上网时段结束时退出登录并以正确步骤离开数钱吧平台。注册账户因您主动泄露或因您遭受他人攻击、诈骗等行为导致的损失及后果，数钱吧并不承担责任，您应通过司法、行政等救济途径向侵权行为人追偿。").append(FileUtils.separator);
                sb.append("3.4您不得将在数钱吧平台注册获得的账户借给他人使用，否则您应承担由此产生的全部责任，并与实际使用人承担连带责任。").append(FileUtils.separator);
                sb.append("3.5您同意，数钱吧平台有权使用您的注册信息、用户名、密码等信息，登录进入您的注册账户，进行证据保全，包括但不限于公证、见证等。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("四、用户信息").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("4.1您应自行诚信的向数钱吧平台提供注册资料，确认提供的注册资料真实、准确、完整、合法有效，注册资料如有变动的，应及时更新其注册资料。如果您提供的注册资料不合法、不真实、不准确、不详尽的，您需承担因此引起的相应责任及后果，并且数钱吧保留终止您使用数钱吧平台服务的权利。").append(FileUtils.separator);
                sb.append("4.2您在数钱吧进行浏览网页、使用服务等活动时，涉及用户真实姓名/名称、通信地址、联系电话、电子邮箱等隐私信息的，数钱吧将予以严格保密信息。").append(FileUtils.separator);
                sb.append("4.3您同意，数钱吧拥有通过短信电话，页面广告展示等形式，向在您发送服务信息、促销活动等告知信息的权利；您同意并授权数钱吧将您的用户信息（包括但不限于姓名/名称、证件号、联系方式、信用状况）和交易信息披露给与您交易的另一方或数钱吧为您提供相关服务而进行合作的第三方、监管机构、协会等自律组织。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("五、 用户依法言行义务").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("5.1本协议依据国家相关法律法规规章制定，您同意严格遵守以下义务：").append(FileUtils.separator);
                sb.append("(1)不得传输或发表：煽动抗拒、破坏宪法和法律、行政法规实施的言论，煽动颠覆国家政权，推翻社会主义制度的言论，煽动分裂国家、破坏国家统一的言论，煽动民族仇恨、民族歧视、破坏民族团结的言论；").append(FileUtils.separator);
                sb.append("(2)从中国大陆向境外传输资料信息时必须符合中国有关法规；").append(FileUtils.separator);
                sb.append("(3)不得利用数钱吧平台从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动；").append(FileUtils.separator);
                sb.append("(4)不得干扰数钱吧平台的正常运转，不得侵入数钱吧平台或国家计算机信息系统；").append(FileUtils.separator);
                sb.append("(5)不得传输或发表任何违法犯罪的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的等信息资料；").append(FileUtils.separator);
                sb.append("(6)不得传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论；").append(FileUtils.separator);
                sb.append("(7)不得教唆他人从事本条所禁止的行为；").append(FileUtils.separator);
                sb.append("(8)不得利用在数钱吧平台注册的账户进行牟利性经营活动；").append(FileUtils.separator);
                sb.append("(9)不得发布任何侵犯他人著作权、商标权等知识产权或合法权利的内容。").append(FileUtils.separator);
                sb.append("5.2若您未遵守以上规定，数钱吧有权作出独立判断并采取暂停或关闭账户等措施。您须对自己在网上的言论和行为承担法律责任。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("六、知识产权条款").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("6.1数钱吧是数钱吧平台相应网站或移动客户端的制作者,拥有数钱吧平台上内容及资源的著作权等合法权利,受国家法律保护。除法律另有强制性规定外，未经数钱吧明确的书面许可,任何单位或个人不得以任何方式非法地全部或部分复制、转载、引用、链接、抓取或以其他方式使用数钱吧平台的信息内容，否则，数钱吧有权追究其法律责任。").append(FileUtils.separator);
                sb.append("6.2数钱吧平台所刊登的资料信息（诸如文字、图表、标识、按钮图标、图像、声音文件片段、数字下载、数据编辑和软件），均是数钱吧或其内容提供者的财产，受中国和国际版权法的保护。数钱吧平台上所有内容的汇编是数钱吧的排他财产，受中国和国际版权法的保护。数钱吧平台上所有软件都是数钱吧或其关联公司或其软件供应商的财产，受中国和国际版权法的保护。").append(FileUtils.separator);
                sb.append("6.3您一旦接受本协议，即表明您主动将您在任何时间段在数钱吧平台发表的任何形式的信息内容（包括但不限于客户评价、客户咨询、各类话题文章等信息内容）的财产性权利等任何可转让的权利，如著作权财产权（包括并不限于：复制权、发行权、出租权、展览权、表演权、放映权、广播权、信息网络传播权、摄制权、改编权、翻译权、汇编权以及应当由著作权人享有的其他可转让权利），全部独家且不可撤销地转让给数钱吧所有，您同意数钱吧有权就任何主体侵权而单独提起诉讼。").append(FileUtils.separator);
                sb.append("6.4本协议已经构成相关法律规定的著作财产权等权利转让书面协议，其效力及于您在数钱吧平台上发布的任何受著作权法保护的作品内容，无论该等内容形成于本协议订立前还是本协议订立后。").append(FileUtils.separator);
                sb.append("6.5您同意并已充分了解本协议的条款，承诺不将已发表于数钱吧平台的信息，以任何形式发布或授权其它主体以任何方式使用（包括但不限于在各类网站、媒体上使用）。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("七、责任限制及不承诺担保").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("7.1除非另有明确的书面说明, 数钱吧平台及其所包含的或以其它方式通过网站或移动客户端提供给您的全部信息、内容、材料、产品（包括软件）和服务，均是在'按现状'和'按现有'的基础上提供的。除非另有明确的书面说明,数钱吧不对数钱吧平台的运营及其包含在数钱吧平台上的信息、内容、材料、产品（包括软件）或服务作任何形式的、明示或默示的声明或担保（根据中华人民共和国法律另有规定的以外）。").append(FileUtils.separator);
                sb.append("7.2数钱吧不担保数钱吧平台所包含的或以其它方式通过数钱吧平台提供给您的全部信息、内容、材料、产品（包括软件）和服务、其服务器或从数钱吧平台发出的电子信件、信息没有病毒或其他有害成分。").append(FileUtils.separator);
                sb.append("7.3如因不可抗力或其它数钱吧平台无法控制的原因使数钱吧平台系统崩溃或无法正常使用导致数钱吧平台服务无法完成或丢失有关的信息、记录等，数钱吧会合理地尽力协助处理善后事宜。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("八、条款的解释、法律适用及争端解决").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("8.1本协议是由您与数钱吧平台共同签订的，适用于您在数钱吧平台的全部活动。").append(FileUtils.separator);
                sb.append("8.2本协议不涉及您与其他平台用户之间因网上交易而产生的法律关系及法律纠纷。但您在此同意将全面接受并履行与其他平台用户在数钱吧平台签订的任何电子法律文本，并承诺按照该法律文本享有和（或）放弃相应的权利、承担和（或）豁免相应的义务。").append(FileUtils.separator);
                sb.append("8.3 如本协议中的任何条款无论因何种原因完全或部分无效或不具有执行力，则应认为该条款可与本协议相分割，并可被尽可能接近各方意图的、能够保留本协议要求的经济目的的、有效的新条款所取代，而且，在此情况下，本协议的其他条款仍然完全有效并具有约束力。").append(FileUtils.separator);
                sb.append("8.4本协议及其修订的有效性、履行与本协议及其修订效力有关的所有事宜，将受中国大陆法律管辖，任何争议仅适用中国法律。").append(FileUtils.separator);
                sb.append("8.5本协议签订地为中国杭州市萧山区。因本协议所引起的您与数钱吧的任何纠纷或争议，首先应友好协商解决，协商不成的，您在此完全同意将纠纷或争议提交合同签订地有管辖权的人民法院诉讼解决。").append(FileUtils.separator);
                map.put("content", sb.toString());
            }
            return ResultDTO.success(map);
        } catch (Exception e) {
            logger.error("获取协议异常", e);
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }

    }
    //建议
    @Override
    public ResultDTO suggest(SuggestDTO suggestDTO) {
        if(suggestDTO.getUserId()==null){
            return ResultDTO.fail(ApiConstant.E_USER_ID_NULL);
         }
        if(Strings.isNullOrEmpty(suggestDTO.getContent())){
            return ResultDTO.fail(ApiConstant.E_SUGGESTEMPTY_ERROR);
        }
        SysSuggest sysSuggest=new SysSuggest();
        sysSuggest.setAppUserId(suggestDTO.getUserId());
        //根据id查找到手机号码
        AppUser appUser=appUserDao.selectAppUserById(suggestDTO.getUserId());
        if(appUser==null){
            return ResultDTO.fail(ApiConstant.E_NOREGISTER_LOGIN);
        }
        sysSuggest.setMobile(appUser.getMobile());
        sysSuggest.setContent(suggestDTO.getContent());
        sysSuggest.setSubmitTime(new Date());
        sysSuggest.setState("0");
        sysSuggestDao.insert(sysSuggest);
        return ResultDTO.successForSubmit();
    }

}
