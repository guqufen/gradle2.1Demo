package net.fnsco.web.controller.e789.third.ticket.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import net.fnsco.core.base.VO;

public class TetterVO extends VO {
    public static void setList(List<TetterVO> resultList, PyCommonVO<SiteVO> resultVO) {
        TetterVO temp = new TetterVO();
        temp.setName("A");
        temp.setList(resultVO.getA());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("B");
        temp.setList(resultVO.getB());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("C");
        temp.setList(resultVO.getC());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("D");
        temp.setList(resultVO.getD());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("E");
        temp.setList(resultVO.getE());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("F");
        temp.setList(resultVO.getF());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("G");
        temp.setList(resultVO.getG());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("H");
        temp.setList(resultVO.getH());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("I");
        temp.setList(resultVO.getI());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("J");
        temp.setList(resultVO.getJ());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("K");
        temp.setList(resultVO.getK());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("L");
        temp.setList(resultVO.getL());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("M");
        temp.setList(resultVO.getM());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("N");
        temp.setList(resultVO.getN());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("O");
        temp.setList(resultVO.getO());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("P");
        temp.setList(resultVO.getP());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("Q");
        temp.setList(resultVO.getQ());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("R");
        temp.setList(resultVO.getR());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("S");
        temp.setList(resultVO.getS());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("T");
        temp.setList(resultVO.getT());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("U");
        temp.setList(resultVO.getU());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("V");
        temp.setList(resultVO.getV());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("W");
        temp.setList(resultVO.getW());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("X");
        temp.setList(resultVO.getX());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("Y");
        temp.setList(resultVO.getY());
        resultList.add(temp);
        temp = new TetterVO();
        temp.setName("Z");
        temp.setList(resultVO.getZ());
        resultList.add(temp);
    }

    @ApiModelProperty(value = "所属字母", example = "所属字母例如：a，b，c")
    private String       name;
    @ApiModelProperty(value = "字母下的所有站点", example = "字母下的所有站点")
    private List<SiteVO> list;

    /**
     * name
     *
     * @return  the name
     * @since   CodingExample Ver 1.0
    */

    public String getName() {
        return name;
    }

    /**
     * name
     *
     * @param   name    the name to set
     * @since   CodingExample Ver 1.0
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * list
     *
     * @return  the list
     * @since   CodingExample Ver 1.0
    */

    public List<SiteVO> getList() {
        return list;
    }

    /**
     * list
     *
     * @param   list    the list to set
     * @since   CodingExample Ver 1.0
     */

    public void setList(List<SiteVO> list) {
        this.list = list;
    }

}
