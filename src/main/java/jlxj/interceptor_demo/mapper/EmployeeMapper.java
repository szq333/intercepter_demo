package jlxj.interceptor_demo.mapper;

import jlxj.interceptor_demo.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 阿强
 * @since 2025-03-24
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
