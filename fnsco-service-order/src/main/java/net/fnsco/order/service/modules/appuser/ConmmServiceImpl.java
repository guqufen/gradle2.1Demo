package net.fnsco.order.service.modules.appuser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import net.fnsco.bigdata.service.dao.master.SysBankDao;
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
    public ResultDTO<VersionResultDTO> queryLastVersionInfo(VersionDTO sysVersionDTO) {
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
    public ResultDTO<Map<String, String>> getProtocol(ProtocolDTO protocolDTO) {
        Map<String, String> map = new HashMap<>();
        try {
            if (protocolDTO.getProtocol() == 1) {
                map.put("title", "用户服务协议须知");
                StringBuilder sb = new StringBuilder();
                sb.append("").append(FileUtils.separator);
                sb.append("一、提示条款").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("感谢您选择e789服务。e789服务协议（以下简称本协议）由杭州法奈昇科技有限公司（以下简称法奈昇科技）和您签订。").append(FileUtils.separator);
//                sb.append("为了保障您的权益，请在使用数钱吧平台服务（详见定义条款）前，详细阅读并理解本协议（详见定义条款）的所有内容，特别是免除或者限制责任的条款、争议解决和法律适用条款以及开通或使用某项服务的单独协议，及选择接受或不接受的部分。一旦您采取以合理的理解表明您希望与数钱吧平台签订本协议的行为（例如，点击按钮上书写“同意《数钱吧平台注册协议》”或类似文字，且页面上同时列明了本协议的内容或者可以有效展示本协议内容的链接；或您下载、安装、获取/激活、登录含服务内容的数钱吧平台并使用服务），即视为您已充分阅读、理解并接受本协议的全部内容，并与数钱吧平台达成协议。如您对本协议有任何疑问的，应向数钱吧平台客服咨询。如果您不同意本协议的约定，您应立即停止使用数钱吧平台服务。").append(FileUtils.separator);
//                sb.append("您与数钱吧平台达成协议后，您承诺接受并遵守本协议的约定，并不得以未阅读本协议的内容或者未获得平台对您问询的解答等理由，主张本协议无效，或要求撤销本协议。在本协议履行过程中，数钱吧平台可以依其单独判断暂时停止提供、限制或改变平台服务。一旦本协议的内容发生变动，数钱吧平台将通过平台公布最新的服务协议，不再向您作个别通知。如果您不同意数钱吧平台对本服务协议所做的修改，您有权停止使用平台服务。如果您继续使用数钱吧平台服务，则视为您接受数钱吧平台对本协议所做的修改，并应遵照修改后的协议执行。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("二、e789服务协议的确认").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("2.1本协议有助于您了解e789为您提供的服务内容及您使用服务的权利和义务，请您仔细阅读（特别是以粗体标注的内容）。如果您对本协议的条款有疑问，您可通过本网站（下文定义）的客服电话400-1818-823进行咨询。").append(FileUtils.separator);
                sb.append("2.2如本协议发生变更，法奈昇科技将通过http://www.fnsco.net网站公告的方式提前予以公布，变更后的协议在公告期届满起生效。若您在协议生效后继续使用e789服务的，表示您接受变更后的协议，也将遵循变更后的协议使用e789服务。").append(FileUtils.separator);
                sb.append("2.3如您为无民事行为能力人或为限制民事行为能力人，请告知您的监护人，并在您监护人的指导下阅读本协议和使用e789服务。若您是中国大陆以外的用户，您还需同时遵守您所属国家或地区的法律，且您确认，订立并履行本协议不受您所属、所居住或开展经营活动或其他业务的国家或地区法律法规的限制。").append(FileUtils.separator);
//                sb.append("2.4数钱吧注册协议：本协议内容包括协议正文、法律声明、平台规则及所有平台已经发布或将来可能发布的各类规则、公告或通知。").append(FileUtils.separator);
//                sb.append("2.5数钱吧平台规则：包括在数钱吧平台已经发布及后续发布的全部规则、解读、公告等内容以及数钱吧在论坛、帮助中心内发布的各类规则、实施细则、流程说明等。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("三、e789服务相关定义").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("3.1e789会员号（或称“会员号”）：指您按照e789提供的方式取得的，供您使用部分e789服务的编号。会员号登录名是您的手机号码。如您只持有会员号，则不能使用余额服务（下文定义）。").append(FileUtils.separator);
                sb.append("3.2e789账户（或称“账户”）：指您获得e789会员号且通过e789身份验证后取得的供您使用e789服务的支付账户。您取得e789账户后，会员号登录名即成为您e789账户登录名。除本协议另有约定外，e789账户持有人可以使用余额服务。").append(FileUtils.separator);
                sb.append("3.3e789登录名：指您注册e789会员号或e789账户时提供的用于登录的手机号，分为会员号登录名和账户登录名两种。").append(FileUtils.separator);
                sb.append("3.4身份要素:指e789用于识别您身份的信息要素，包括但不限于您的会员号、e789账户、密码、数字证书、短信校验码、电话号码、手机号码、身份证件号码及指纹信息。").append(FileUtils.separator);
                sb.append("3.5有权机关：指依照法律法规等有权要求查询、冻结或扣划资金等措施的单位，包括但不限于公安机关、检察院、法院、海关、税务机关。").append(FileUtils.separator);
                sb.append("3.6冻结：指有权机关要求的冻结。被冻结余额不能用于支付、提现等，被冻结账户不可登录、使用。").append(FileUtils.separator);
                sb.append("3.7限制：指除冻结情况之外，e789会员号或账户部分或全部功能不能使用，以及会员号或账户相关资产、账户余额、第三方提供的授信额度不能使用。").append(FileUtils.separator);
                sb.append("3.8止付：限制措施的一种，指e789账户余额不能使用，例如不能用于支付、提现等服务。").append(FileUtils.separator);
                sb.append("3.9余额服务：指基于e789账户余额可以使用的充值、消费、收款等服务。除本协议另有规定外，余额服务的功能及收付款额度将按照《非银行支付机构网络支付业务管理办法》及其他监管规定进行调整。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("四、e789为您提供的服务内容").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("4.1e789服务是e789向您提供的非金融机构支付服务，是受您委托代您收款或付款的资金转移服务。其中，代收代付款项服务是指e789为您提供的代为收取或代为支付款项的服务。通过代收服务，您可以收到他人向您支付的款项，具体是指自您委托e789将您银行卡内的资金充值到您的e789账户或委托e789代为收取第三方向您支付的款项之时起至根据您的指令将该等款项的全部或部分实际入账到您的银行账户或e789账户之时止（含提现）的整个过程。通过代付服务，您可以支付款项给您指定的第三方。代付具体是指自款项从您指定账户（非e789账户）出账之时起至e789根据您或有权方给出的指令将上述款项的全部或部分入账到第三方的银行账户或e789账户之时止的整个过程；或自您根据本协议委托e789将您银行卡的资金充值到您或他人的e789账户或自您因委托e789代收相关款项并入账到您的e789账户之时起至委托e789根据您或有权方给出的指令将上述款项的全部或部分入账第三方的银行账户或e789账户之时止的整个过程。").append(FileUtils.separator);
                sb.append("4.2e789代收代付后，非经法律程序或者非依本协议之约定，该指令是不可撤销的。代收代付款项服务的功能有如下几类：").append(FileUtils.separator);
                sb.append("(1)充值，您可以将您银行卡内的资金充值到您的e789账户。").append(FileUtils.separator);
                sb.append("(2)提现，您可以将您e789账户内余额划转至您名下的可接收款项的中国大陆银行账户。除被冻结、止付或其他限制款项外，e789将于收到提现指令后的合理时间内，将相应款项汇入该银行账户。此外，我们可能会对提现进行风险审查，因此可能导致提现延迟。").append(FileUtils.separator);
                sb.append("(3)e789中介服务，亦称“e789担保交易”， 指为了解决买家和卖家网上交易的信任问题，买家按照流程点击确认收货后，e789再将代收的买家支付的款项代为支付给卖家。除本协议另有规定外，发生交易纠纷时，您不可撤销授权e789根据证据决定将争议款项的全部或部分支付给交易一方或双方。").append(FileUtils.separator);
                sb.append("(4)即时到账服务，不同于e789中介交易，指买家付款后，该款项无需等买家确认收货，即付给卖家的付款方式。该项服务一般适用于您与交易对方彼此都有充分信任的交易，买卖双方需要自行承担所有交易风险并自行处理纠纷。").append(FileUtils.separator);
                sb.append("(5)后付服务，买方选择特定服务（以下简称“特定服务”）作为资金渠道的，e789将根据特定服务的约定提供款项的代付服务。使用e789服务时， 该笔交易项下的款项将在买家付款或确认收货时由e789代付至卖家的账户。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("五、 e789会员号和账户的注册、使用和注销").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("5.1注册").append(FileUtils.separator);
                sb.append("(1)您完成e789账号注册并激活后，即可使用e789服务。具体验证方式以本网站页面提示为准。").append(FileUtils.separator);
                sb.append("(2)您在会员号或账户中设置的昵称、头像、签名、留言等必须遵守法律法规、公序良俗、社会公德，且不会侵害其他第三方的合法权益，否则e789可能会取消您的昵称、头像、签名。").append(FileUtils.separator);
//                sb.append("(3)不得利用数钱吧平台从事洗钱、窃取商业秘密、窃取个人信息等违法犯罪活动；").append(FileUtils.separator);
//                sb.append("(4)不得干扰数钱吧平台的正常运转，不得侵入数钱吧平台或国家计算机信息系统；").append(FileUtils.separator);
//                sb.append("(5)不得传输或发表任何违法犯罪的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽的、不文明的等信息资料；").append(FileUtils.separator);
//                sb.append("(6)不得传输或发表损害国家社会公共利益和涉及国家安全的信息资料或言论；").append(FileUtils.separator);
//                sb.append("(7)不得教唆他人从事本条所禁止的行为；").append(FileUtils.separator);
//                sb.append("(8)不得利用在数钱吧平台注册的账户进行牟利性经营活动；").append(FileUtils.separator);
//                sb.append("(9)不得发布任何侵犯他人著作权、商标权等知识产权或合法权利的内容。").append(FileUtils.separator);
                sb.append("5.2使用:身份要素是e789识别您身份的依据，请您务必妥善保管。使用身份要素进行的任何操作、发出的任何指令均视为您本人做出。因您的原因造成的账户、密码等信息被冒用、盗用或非法使用，由此引起的一切风险、责任、损失、费用等应由您自行承担。您同意：").append(FileUtils.separator);
                sb.append("(1)基于不同的终端以及您的使用习惯，我们可能采取不同的验证措施识别您的身份。例如您的e789账户在新设备首次登录的，我们可能通过密码加校验码的方式识别您的身份。").append(FileUtils.separator);
                sb.append("(2)为保障您的资金安全，请把手机及其他设备的密码设置成与e789会员号及账户的密码不一致。如您发现有他人冒用或盗用您的会员号、账户或者e789登录名及密码，或您的手机或其他有关设备丢失时，请您立即以有效方式通知e789；您还可以向e789申请暂停或停止e789服务,以保障您的合法权益。您理解e789对您的请求采取行动需要合理时间，如e789未在合理时间内采取有效措施，导致您损失扩大的，e789将就扩大的损失部分承担责任，但e789对采取行动之前已执行的指令不承担责任。").append(FileUtils.separator);
                sb.append("(3)e789会员号和账户仅限您本人使用，不可转让、借用、赠与、继承，但e789账户内的相关财产权益可被依法继承。").append(FileUtils.separator);
                sb.append("(4)基于运行和交易安全的需要，e789可以暂停或者限制e789服务部分功能，或增加新的功能。").append(FileUtils.separator);
                sb.append("(5)为了维护良好的网络环境，e789有时需要了解您使用e789服务的真实背景及目的，如e789要求您提供相关信息或资料的，请您配合提供。").append(FileUtils.separator);
                sb.append("(6)为了您的交易安全，您在使用e789服务时，请事先判断交易对方是否具有完全民事行为能力并谨慎决定是否使用e789服务与对方进行交易。").append(FileUtils.separator);
                sb.append("5.3注销:在需要终止使用e789服务时，符合以下条件的，您可以申请注销您的会员号或e789账户：").append(FileUtils.separator);
                sb.append("(1)您仅能申请注销您本人的会员号或账户，并依照e789的流程进行注销。").append(FileUtils.separator);
                sb.append("(2)您可以通过自助或者人工的方式申请注销会员号或账户，但如果您使用了e789提供的安全产品，请在该安全产品环境下申请注销。").append(FileUtils.separator);
                sb.append("(3)您申请注销的会员号或账户处于正常状态，即您的会员号或账户的信息是最新、完整、正确的，且会员号或账户未被采取止付、冻结等限制措施。").append(FileUtils.separator);
                sb.append("(4)为了维护您和其他用户的合法利益，您申请注销的会员号或账户，应当不存在未了结的权利义务或其他因为注销该账户会产生纠纷的情况，不存在任何未完结交易，没有余额等资产。").append(FileUtils.separator);
                sb.append("(5)根据《非银行支付机构网络支付业务管理办法》规定，e789不得为金融机构，以及从事信贷、融资、理财、担保、信托、货币兑换等金融业务的其他机构开立e789账户。如您属于金融机构或从事信贷、融资、理财、担保、信托、货币兑换等金融业务的其他机构，且您持有e789账户的，则您同意按照e789流程尽快将该e789账户内资金提现或通过转账等途径支用完毕，并授权e789将该账户注销，但您仍可使用支e789基于银行账户向您提供的支付服务。").append(FileUtils.separator);
                sb.append("(6)如您在APP上有欺诈、发布或销售伪劣商品、侵犯他人合法权益或其他严重违反e789规则的行为，e789可以注销您名下的会员号或账户，您将不能再用该会员号或账户的e789登录名登录，所有e789的服务将同时终止。").append(FileUtils.separator);
                sb.append("(7)e789会员号或账户注销后，您将无法使用e789的任何服务，双方的权利义务终止（本协议另有约定不得终止的或依其性质不能终止的除外）， 同时还可能产生如下结果：").append(FileUtils.separator);
                sb.append("(7.1)任何兑换代码或卡券均将作废。").append(FileUtils.separator);
                sb.append("(7.2)您仍应对您在注销会员号或账户前且使用e789服务期间的行为承担相应责任，包括但不限于可能产生的违约责任、损害赔偿责任及履约义务，同时e789仍可保存您注销前的相关信息。").append(FileUtils.separator);
                sb.append("(7.3)一旦注销成功，账户记录、账户功能等将无法恢复或提供。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("六、使用e789服务的注意事项 为有效保障您使用e789服务时的合法权益，e789提醒您注意以下事项：").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("6.1身份验证").append(FileUtils.separator);
                sb.append("(1)您在注册、使用e789服务的过程中，请您提供合法、真实、有效、准确并完整的资料（包括但不限于身份证、户口本、护照、营业执照、开户许可证等证明、联系方式、从事职业、通讯地址）。 为了能将账户资金变动及时通知到您，以及更好保障您的账户安全，如该等资料发生变更，请您及时通知e789。 为了及时有效地验证您的信息（包括但不限于身份信息、账户信息、购汇额度），根据法律法规及监管规定或e789认为有需要时，您同意我们可以把您的信息提供给第三方，也同意第三方可以把您的信息提供给e789，以便e789进行验证。您应确保e789登录名、会员号或账户绑定的手机号均为您本人持有，如您占用了他人的手机号，为避免给手机号持有人带来不便或不利影响，也为了您的资金安全，e789可能将该手机号从您的会员号或账户中删除并解除关联。").append(FileUtils.separator);
                sb.append("(2)若您为个人用户，在出现下列情形时，请您积极配合e789核对您的有效身份证件或其他必要文件，留存有效身份证件的彩色扫描件，且完成e789要求的相关身份验证：").append(FileUtils.separator);
                sb.append("(2.1)您办理充值、收取或支付单笔金额人民币1万元以上或者外币等值1000美元以上业务的；").append(FileUtils.separator);
                sb.append("(2.2)您名下全部e789账户项下30天内充值、收取及支付总金额累计人民币5 万元以上或外币等值1万美元以上的；").append(FileUtils.separator);
                sb.append("(2.3)您名下全部e789账户余额连续10天超过人民币5000元或外币等值1000美元的； ").append(FileUtils.separator);
                sb.append("(2.4)您使用e789服务买卖金融产品或服务的；").append(FileUtils.separator);
                sb.append("(2.5)您购买e789记名预付卡或办理记名预付卡充值的；").append(FileUtils.separator);
                sb.append("(2.6)您一次性购买不记名预付卡超过人民币1万元，或通过不记名预付卡为e789账户累计充值超过人民币1万的；").append(FileUtils.separator);
                sb.append("(2.7)您要求变更身份信息或您身份信息已过有效期的；").append(FileUtils.separator);
                sb.append("(2.8)e789认为您的交易行为或交易情况出现异常的；").append(FileUtils.separator);
                sb.append("(2.9)e789认为您的身份信息或相关资料存在疑点或e789在向您提供服务的过程中认为您已经提供的身份信息或相关资料存在疑点的；").append(FileUtils.separator);
                sb.append("(2.10)e789认为应核对或留存您身份证件或其他必要文件的其他情形的。 本条款所称“以上”,包括本数。").append(FileUtils.separator);
                sb.append("6.2为了满足相关监管规定的要求，您同意按照e789的要求及通知的时间提供您的身份信息以完成身份验证，否则您可能无法进行收款、提现、余额支付、购买理财产品等操作，且e789可能对您的账户余额进行止付或注销您的账户。").append(FileUtils.separator);
                sb.append("6.3存在如下情形时，e789会对您名下会员号或/及e789账户暂停或终止提供服务，或对余额进行止付，且有权限制您所使用的产品或服务功能（包括但不限于对这些会员号或/及账户名下的款项和在途交易采取止付、取消交易、调账等限制措施）：").append(FileUtils.separator);
                sb.append("6.4您违反了本协议的约定；根据法律法规及法律文书的规定；根据有权机关的要求；您的会员号或账户操作、资金流向等存在异常时；您的会员号或账户可能产生风险的；您在参加市场活动时有批量注册e789会员号或账户、刷信用、交易内容不符或作弊等违反活动规则、违反诚实信用原则的；他人向您e789账户错误汇入资金等导致您可能存在不当获利的；您遭到他人投诉，且对方已经提供了一定证据的；您可能错误地操作他人e789会员号或账户，或者将他人会员号或账户进行了身份验证的。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("七、e789服务规则").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("7.1为了您能获得良好的支付体验，您理解并认可，e789可根据您的支付习惯或e789推荐的设置向您提供服务，如根据您的支付习惯进行支付渠道的排序。").append(FileUtils.separator);
                sb.append("7.2您使用拍码方式或使用 “当面付”进行支付时，您无需输入密码即可在一定额度内完成支付，e789会按照商家确定的金额从您的资产（扣款渠道视情况而定）里扣款给商家。").append(FileUtils.separator);
                sb.append("7.3e789服务中您可使用的收付款额度、日累计转账限额和笔数，可能因您使用该支付服务时所处的国家/地区、监管要求、支付场景、银行额度控制、e789风险控制、身份要素验证等事由而有不同，具体请见本网站页面提示或公告。").append(FileUtils.separator);
                sb.append("7.4您认可e789会员号或账户的使用记录、交易数据等均以e789系统记录为准。如您对该等数据存有异议的，可及时向e789提出异议，并提供相关证据供e789核实。").append(FileUtils.separator);
                sb.append("7.5如您使用e789代扣服务的，下面任一情况下均会导致扣款不成功：您指定的账户余额不足；您指定的会员号或账户已被采取止付、冻结或其他限制使用权限的措施；超出监管部门、e789或银行规定的付款额度。").append(FileUtils.separator);
                sb.append("7.6对于您提供及发布文字、图片、视频、音频等非个人隐私信息，在版权保护期内您免费授予e789及其关联公司获得全球排他的许可使用权利及再授权给其他第三方使用的权利。您同意e789及其关联公司存储、使用、复制、修订、编辑、发布、展示、翻译、分发您的非个人隐私信息或制作其派生作品，并以已知或日后开发的形式、媒体或技术将上述信息纳入其它作品内。").append(FileUtils.separator);
                sb.append("7.7您在使用e789服务过程中，本协议内容、网页上出现的关于操作的提示或e789发送到手机的信息内容是您使用e789服务的相关规则。").append(FileUtils.separator);
                sb.append("7.8e789会以网站公告、发送短信、电话、站内信或客户端通知等方式向您发送通知，例如通知您交易进展情况，或者提示您进行相关操作，请您及时予以关注。").append(FileUtils.separator);
                sb.append("7.9您应自行承担您使用e789服务期间的任何货币贬值、汇率波动及收益损失等风险，您仅在e789代收代付款项（不含被冻结、止付或其他限制的款项）的金额范围内享有对该等款项支付、提现的权利，您对所有代收代付款项（含被冻结、止付或受限制的款项）产生的任何收益（包括但不限于利息和其他孳息）不享有任何权利。e789就所有该代收代付款项产生的任何收益（包括但不限于利息和其他孳息）享有所有权。").append(FileUtils.separator);
                sb.append("7.10您在使用e789的服务过程中，如遇到问题，您可以通过拨打400-1818-823联系我们。如您被他人投诉或投诉他人，我公司将有权将您的主体信息、联系方式、投诉相关的内容等必要信息提供给争议相对方或相关部门，以便及时解决投诉纠纷，以保护您及他人的合法权益；但法律法规另有规定的除外。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("八、e789服务的承诺和声明").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("8.1e789并非银行，本协议项下涉及的资金转移均通过银行来实现，您接受资金在转移途中产生的合理时间。").append(FileUtils.separator);
                sb.append("8.2您理解，基于相关法律法规，对本协议项下的任何服务，e789均不提供任何担保、垫资。").append(FileUtils.separator);
                sb.append("8.3e789会将您委托e789代收或代付的款项，严格按照法律法规或有权机关的监管要求进行管理.e789提醒您注意，e789账户所记录的资金余额不同于您本人的银行存款，不受《存款保险条例》保护，其实只为您委托e789保管的、所有权归属于您的预付价值。该预付价值对应的货币资金虽然属于您，但不以您本人名义存放在银行，而是以e789名义存放在银行，并且由e789向银行发起资金调拨指令。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("九、交易风险提示").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("9.1在使用e789服务时，若您或您的交易对方未遵从本协议或相关网站说明、交易、支付页面中之操作提示、规则，则e789有权拒绝为您与交易对方提供服务，且e789不承担损害赔偿责任。若发生前述情形，而款项已先行入账您的e789账户，您同意e789有权直接自相关账户中扣回相应款项及（或）禁止您要求支付此笔款项之权利。").append(FileUtils.separator);
                sb.append("9.2您使用e789服务购买金融类产品时，为了您的利益，建议您仔细阅读金融类产品的协议以及页面提示，确认销售机构及产品信息，谨慎评估风险后再购买。").append(FileUtils.separator);
                sb.append("9.3在您使用e789服务时，e789有权依照《e789服务收费规则》向您收取服务费用。e789拥有制订及调整服务费之权利，具体服务费用以您使用e789服务时本网站或产品页面上所列之收费方式公告或您与e789达成的其他书面协议为准。除非另有说明或约定，您同意e789有权自您委托e789代收或代付的款项或您任一e789账户余额或者其他资产中直接扣除上述服务费用。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("十、积分").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("10.1就您使用e789服务过程中e789向您授予的积分，无论该积分以何种方式获得，您都不得使用该积分换取任何现金或金钱。").append(FileUtils.separator);
                sb.append("10.2积分并非您拥有所有权的财产，e789有权单方面调整积分数值或调整e789的积分规则。").append(FileUtils.separator);
                sb.append("10.3您有权按e789的积分规则，将所获积分交换e789指定的服务或权益。").append(FileUtils.separator);
                sb.append("10.4如e789有合理理由怀疑您的积分的获得及（或）使用存有欺诈、滥用或其它e789认为不当的行为，e789可随时取消您的积分，或限制、终止您的积分使用。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("十一、用户合法使用e789服务的承诺").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("11.1您应遵守中华人民共和国相关法律法规及您所属、所居住或开展经营活动或其他业务的国家或地区的法律法规，不得将e789服务用于任何非法目的（包括用于禁止或限制交易物品的交易），也不得以任何非法方式使用e789服务。").append(FileUtils.separator);
                sb.append("11.2您不得利用e789服务从事侵害他人合法权益之行为或违反国家法律法规， 否则e789有权进行调查、延迟或拒绝结算或停止提供e789服务，且您应独立承担所有相关法律责任，因此导致e789或e789雇员或其他方受损的，您应承担赔偿责任。").append(FileUtils.separator);
                sb.append("11.3上述1和2适用的情况包括但不限于：（1）侵害他人名誉权、隐私权、商业秘密、商标权、著作权、专利权等合法权益。（2）违反依法定或约定之保密义务。（3）冒用他人名义使用e789服务。（4）从事不法交易行为， 如洗钱、恐怖融资、贩卖枪支、毒品、禁药、盗版软件、黄色淫秽物品、其他e789认为不得使用e789服务进行交易的物品等。（5）提供赌博资讯或以任何方式引诱他人参与赌博。（6）未经授权使用他人银行卡， 或利用信用卡套取现金（以下简称套现）。（7）进行与您或交易对方宣称的交易内容不符的交易， 或不真实的交易。（8）从事任何可能侵害e789服务系统、数据之行为。").append(FileUtils.separator);
                sb.append("11.4您理解，e789服务有赖于系统的准确运行及操作。若出现系统差错、故障或其他原因引发的展示错误、您或e789不当获利等情形，您同意e789可以采取更正差错、扣划款项等适当纠正措施。").append(FileUtils.separator);
                sb.append("11.5您不得对e789系统和程序采取反向工程手段进行破解，不得对上述系统和程序（包括但不限于源程序、目标程序、技术文档、客户端至服务器端的数据、服务器数据）进行复制、修改、编译、整合或篡改，不得修改或增减e789系统的任何功能。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("十二、权益保障及隐私保护").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("12.1客户权益保障承诺,e789一直秉承“客户第一”的原则，努力为您带来微小而美好的改变。").append(FileUtils.separator);
                sb.append("12.2用户资金保障,e789始终重视保护用户的资金安全，将自有资金和用户资金分开存放，承诺不挪用、占用用户资金。如您在使用e789服务过程中，由于您的e789账户被盗用造成资金损失，经e789同意，您可授权e789代表您向相关责任第三方或赔付机构进行追偿。您与e789公司另有约定的除外。详见《e789安全保障规则》。").append(FileUtils.separator);
                sb.append("12.3隐私权保护,e789重视对您隐私的保护。关于您的身份资料和其他特定资料依《e789隐私权政策》受到保护与规范，详情请参阅《e789隐私权政策》。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("十三、不可抗力、免责及责任限制").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("12.1免责条款 因下列原因导致e789无法正常提供服务，e789不承担责任：").append(FileUtils.separator);
                sb.append("(1)e789系统停机维护或升级；").append(FileUtils.separator);
                sb.append("(2)因台风、地震、洪水、雷电或恐怖袭击等不可抗力原因；").append(FileUtils.separator);
                sb.append("(3)用户的电脑软硬件和通信线路、供电线路出现故障的；").append(FileUtils.separator);
                sb.append("(4)用户操作不当或通过非e789授权或认可的方式使用e789服务的；").append(FileUtils.separator);
                sb.append("(5)因病毒、木马、恶意程序攻击、网络拥堵、系统不稳定、系统或设备故障、通讯故障、电力故障、银行原因 、第三方服务瑕疵或政府行为等原因。尽管有前款约定，e789将采取合理行动积极促使服务恢复正常。").append(FileUtils.separator);
                sb.append("12.2e789责任范围及责任限制").append(FileUtils.separator);
                sb.append("(1)e789用户信息是由用户本人自行提供的，e789无法保证该信息之准确、有效和完整。").append(FileUtils.separator);
                sb.append("(2)e789可能同时为您及您的（交易）对手方提供e789服务，您同意对e789可能存在的该等行为予以明确豁免任何实际或潜在的利益冲突，并不得以此来主张e789在提供e789服务时存在法律上的瑕疵。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("十四、知识产权的保护").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("14.1e789及关联公司所有系统及本网站上所有内容，包括但不限于著作、图片、档案、资讯、资料、网站架构、网站画面的安排、网页设计，均由e789或e789关联公司依法拥有其知识产权，包括但不限于商标权、专利权、著作权、商业秘密等。").append(FileUtils.separator);
                sb.append("14.2非经e789或e789关联公司书面同意，任何人不得擅自使用、修改、反向编译、复制、公开传播、改变、散布、发行或公开发表本网站程序或内容。").append(FileUtils.separator);
                sb.append("14.3尊重知识产权是您应尽的义务，如有违反，您应承担损害赔偿责任。").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("十五、法律适用与管辖").append(FileUtils.separator);
                sb.append("").append(FileUtils.separator);
                sb.append("15.1本协议之效力、解释、变更、执行与争议解决均适用中华人民共和国法律。因本协议产生之争议，均应依照中华人民共和国法律予以处理，并由被告住所地人民法院管辖。").append(FileUtils.separator);
                map.put("content", sb.toString());
            }else if (protocolDTO.getProtocol() == 2) {
            	map = getTrainTicketsProtocol(map);
            }
            return ResultDTO.success(map);
        } catch (Exception e) {
            logger.error("获取协议异常", e);
            return ResultDTO.fail(ApiConstant.E_APP_CODE_EMPTY);
        }

    }
    
    /**
     * getTrainTicketsProtocol:(获取火车票代购协议)
     *
     * @param  @param maps
     * @param  @return    设定文件
     * @return Map<String,String>    DOM对象
     * @author tangliang
     * @date   2018年1月17日 下午3:54:06
     */
    public Map<String,String> getTrainTicketsProtocol(Map<String,String> map){
    	map.put("title", "火车票代购协议");
        StringBuilder sb = new StringBuilder();
        sb.append("").append(FileUtils.separator);
        sb.append("一、购票说明").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("1.1我司通过铁路官方网站或授权代售点为您提供火车票代购服务；").append(FileUtils.separator);
        sb.append("1.2因全国各铁路局规定与要求不同，代购服务不保证100%代购成功，若代购失败，我司会发起退款，订单金额将原渠道退款到您支付的账户，1-7个工作日到账；").append(FileUtils.separator);
        sb.append("1.3由于火车票票价经常调整，卧铺上中下价格不同，铁路官方随机出票，因此我们在部分席位的代购中，会按照最高价预先收取票款，出票后根据实际票价退还差额；").append(FileUtils.separator);
        sb.append("1.4我们会在火车票开售后，以短信（或微信、邮件等）通知您是否代购成功，由于短信通知有极少数无法及时收到，请及时查看订单状态，以免耽误行程。").append(FileUtils.separator);
        sb.append("1.5火车票代购是人工服务，有时因为网络/人工等问题代购时间可能较长，最迟当前工作日内22:00点前通知代购结果。").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("二、退款说明").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("2.1产品代购失败退款：我司将在确认失败后当日退款至客户原支付渠道。").append(FileUtils.separator);
        sb.append("2.2产品差价退款：如果订单内含有的车票的实际票价低于您所支付的票价，我司将在确认实际票价后将差额退款至原支付渠道。").append(FileUtils.separator);
        sb.append("2.3退款方式：以上产品退款一律按客户订单支付时的原支付渠道退回。如使用支付宝余额进行支付的，退款实时到帐。如果使用银行卡支付，根据银行的不同，预计借记卡1-7个工作日到帐，信用卡1-15个工作日到账，退款到账日期以银行为准，如超7个工作日未到帐，请致电您的银行卡发卡行客服查询。").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("三、免责声明").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("3.1我司提供的是火车票代购服务，您接受本协议，意味着您同意我司及合作商家使用您填写的乘客信息进行代购，包括但不限于授权使用您的乘客信息执行查询、代购、退票、改签、注册等操作，同时您必须遵守12306购票规定的服务条款(https://kyfw.12306.cn/otn/regist/rule)；").append(FileUtils.separator);
        sb.append("3.2我司不对因（1）铁路部门调整车次、票价、坐席等信息（2）铁路部门调整退票规则、改签规则、售票时间（3）客户提供错误的订单信息（包括但不限于姓名、身份证号码、日期、车次、坐席）而造成的用户损失承担任何责任；因上述原因、客户自身原因或其他不可抗力发生的退款或差额以铁路部门实退或实收为准；").append(FileUtils.separator);
        sb.append("3.3由于全国各铁路局会随时调整火车票信息（如车次、票价、坐席、余票），故本网显示产品信息，以及因网络传输问题，我司提供的此类信息可能并非最新或存在误差，因此仅供旅客作为一般参考，任何公司或个人不能将此作为我司做出任何承诺或做出任何保证的依据。").append(FileUtils.separator);
        sb.append("3.4票源紧张，严禁利用本网站进行囤票、占票、倒票，我司有权要求客户提供乘客身份证复印件以核实购票人身份的真实性，否则我司可不提供售后服务，若行为严重到违反铁路机关相关规定的，我司将主动配合公安机关进行查处。").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("四、解决争议适用法律法规约定").append(FileUtils.separator);
        sb.append("").append(FileUtils.separator);
        sb.append("4.1在您的预订生效后，如果在本须知或订单约定内容履行过程中，对相关事宜的履行发生争议，您同意按照中华人民共和国颁布的相关法律法规来解决争议，并同意接受北京市海淀区人民法院的管辖。").append(FileUtils.separator);
        map.put("content", sb.toString());
        return map;
    }
    //建议
    @Override
    public ResultDTO<String> suggest(SuggestDTO suggestDTO) {
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
