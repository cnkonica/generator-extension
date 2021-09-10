package org.mybatis.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.List;

public class GeneratorExtension extends DefaultCommentGenerator {
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        // 生成方法注释
        method.addJavaDocLine("/**");
        String method_name = method.getName();

        if ("deleteByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键删除数据库的记录");
        } else if ("insert".equals(method_name)) {
            method.addJavaDocLine(" * 插入数据库记录");
        } else if ("selectByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键获取一条数据库记录");
        } else if ("updateByPrimaryKey".equals(method_name)) {
            method.addJavaDocLine(" * 根据主键来更新数据库记录");
        } else if ("selectAll".equals(method_name)) {
            method.addJavaDocLine(" * 获取全部数据库记录");
        }else if ("insertSelective".equals(method_name)) {
            method.addJavaDocLine(" * 选择插入数据库记录");
        }else if ("updateByPrimaryKeySelective".equals(method_name)) {
            method.addJavaDocLine(" * 选择根据主键来更新数据库记录");
        }
        method.addJavaDocLine(" *");
        List<Parameter> parameterList = method.getParameters();
        String paramterName;
        for (Parameter parameter : parameterList) {
            paramterName = parameter.getName();
            method.addJavaDocLine(" * @param " + paramterName);
        }
        method.addJavaDocLine(" */");
    }


    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 添加字段注释
        if (introspectedColumn.getRemarks() != null)
            field.addJavaDocLine("/** " + introspectedColumn.getRemarks() + " */");

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // get方法注释
        StringBuffer sb = new StringBuffer();
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 获取：" + introspectedColumn.getRemarks());
        sb.append(" * 表字段：");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");
        sb = new StringBuffer();
        sb.append(" * @return ").append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // set方法注释
        StringBuffer sb = new StringBuffer();
        method.addJavaDocLine("/**");
        method.addJavaDocLine(" * 设置：" + introspectedColumn.getRemarks());
        sb.append(" * 表字段：");
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append('.');
        sb.append(introspectedColumn.getActualColumnName());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" *");
        sb = new StringBuffer();

        Parameter parm = method.getParameters().get(0);
        sb.append("* @param ").append(parm.getName()).append(" ").append(introspectedColumn.getRemarks());
        method.addJavaDocLine(sb.toString());
        method.addJavaDocLine(" */");
    }
}
