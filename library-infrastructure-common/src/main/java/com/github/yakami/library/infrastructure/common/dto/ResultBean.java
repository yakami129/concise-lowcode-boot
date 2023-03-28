package com.github.yakami.library.infrastructure.common.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yakami.library.infrastructure.common.emuns.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan on 2022/6/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResultBean<E> implements Serializable {

    private static final long serialVersionUID = 4209330519056229603L;

    private Integer code;

    private String message;

    private E data;

    public ResultBean() {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
    }

    public ResultBean(E data) {
        this.code = ResultCodeEnum.SUCCESS.getCode();
        this.message = ResultCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    public ResultBean(Integer code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     */
    public boolean success() {
        if(ResultCodeEnum.SUCCESS.getCode().equals(code)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 失败
     */
    public boolean failure() {
        if(ResultCodeEnum.FAIL.getCode().equals(code)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 请求成功
     *
     * @param iPage    分页插件
     * @param dtoClass DTO类型
     * @param <E>
     * @return
     */
    public static final <E> ResultBean<PageResult<E>> succeed(IPage iPage, Class<E> dtoClass) {

        // 批量转换DTO
        batchConvert(iPage, dtoClass);

        return new ResultBean(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), new PageResult(iPage));
    }


    /**
     * 请求成功
     *
     * @return
     */
    public static final <E> ResultBean<PageResult<E>> succeed(IPage<E> iPage) {
        return new ResultBean(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), new PageResult(iPage));
    }

    /**
     * 请求成功
     *
     * @param <E>
     * @return
     */
    public static final <E> ResultBean<E> succeed() {
        return new ResultBean<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 请求成功
     *
     * @param <E>
     * @return
     */
    public static final <E> ResultBean<E> succeed(E data) {
        return new ResultBean<>(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 请求失败
     *
     * @param <E>
     * @return
     */
    public static final <E> ResultBean<E> fail() {
        return new ResultBean<>(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage(), null);
    }

    /**
     * 请求失败
     *
     * @param message 失败日志
     * @param <E>
     * @return
     */
    public static final <E> ResultBean<E> fail(String message) {
        return new ResultBean<>(ResultCodeEnum.FAIL.getCode(), message, null);
    }

    /**
     * 未授权
     *
     * @param <E>
     * @return
     */
    public static final <E> ResultBean<E> unauthorized() {
        return new ResultBean<>(ResultCodeEnum.UNAUTHORIZED.getCode(), ResultCodeEnum.UNAUTHORIZED.getMessage(), null);
    }

    /**
     * 转换为Entity
     *
     * @param obj   DTO对象
     * @param clazz entity的class
     * @param <E>
     * @return
     */
    @SneakyThrows
    public static <E> E toEntity(Object obj, Class<E> clazz) {
        return convert(obj, clazz);
    }

    /**
     * 批量转换为Entity
     *
     * @param objs  DTO对象列表
     * @param clazz entity的class
     * @param <E>
     * @return
     */
    @SneakyThrows
    public static <E> List<E> toEntitys(List objs, Class<E> clazz) {
        return batchConvert(objs, clazz);
    }


    /**
     * 转换为DTO
     *
     * @param obj   entity对象
     * @param clazz DTO的class
     * @param <E>
     * @return
     */
    @SneakyThrows
    public static <E> E toDTO(Object obj, Class<E> clazz) {
        return convert(obj, clazz);
    }

    /**
     * 转换为DTO
     *
     * @param objs  entity对象列表
     * @param clazz DTO的class
     * @param <E>
     * @return
     */
    @SneakyThrows
    public static <E> List<E> toDTOs(List objs, Class<E> clazz) {
        return batchConvert(objs, clazz);
    }


    public static <E> List<E> batchConvert(List<Object> objs, Class<E> clazz) throws InstantiationException, IllegalAccessException {
        List<E> records = new ArrayList<>();
        for (Object obj : objs) {
            records.add(convert(obj, clazz));
        }
        return records;
    }

    public static <E> void batchConvert(IPage iPage, Class<E> dtoClass) {
        List<E> records = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(iPage.getRecords())) {
            iPage.getRecords().forEach(item -> {
                records.add(toDTO(item, dtoClass));
            });
        }
        iPage.setRecords(records);
    }

    private static <E> E convert(Object obj, Class<E> clazz) throws InstantiationException, IllegalAccessException {
        E entity = clazz.newInstance();
        BeanUtils.copyProperties(obj, entity);
        return entity;
    }

}
