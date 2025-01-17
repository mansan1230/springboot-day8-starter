package com.rest.springbootemployee;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    //SUT -> service , DOC -> repository(mocked)
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Test
    void should_update_only_return_all_employees_when_updated_given_em()
    {
        //List<Employee> employees = new ArrayList<>();
        int employeesId = 1;
        Employee employee = new Employee(10,"Susan",22,"Female",10000);
        //employees.add(employee);

        Employee employee2 = new Employee(10,"Tom",23,"Male",10001);


        when(employeeRepository.findById(employeesId)).thenReturn(employee); //stub

        Employee updatedEmployee = employeeService.update(employeesId,employee2);

        //1.verify data
        assertThat(updatedEmployee.getName(),equalTo("Susan"));;
        assertThat(updatedEmployee.getGender(),equalTo("Female"));;
        assertThat(updatedEmployee.getAge(),equalTo(23));
        assertThat(updatedEmployee.getSalary(),equalTo(10001));;

        verify(employeeRepository).findById(employeesId);
    }


    @Test
    void should_return_employees_by_id_when_find_by_id_given_employees(){
        //given
        int employeeId = 1;
        Employee employee = new Employee(employeeId,"Susan",22,"Female",10000);

        when(employeeRepository.findById(employeeId)).thenReturn(employee); //stub

        //when
        Employee result= employeeService.findById(employeeId);

        //then
        assertEquals(employee, result);
        verify(employeeRepository).findById(employeeId);//spy
    }

    @Test
    void should_return_employees_by_gender_when_find_by_gender_given_employees(){
        //given
        Employee employee = new Employee(1,"Susan",22,"Female",10000);
        List<Employee> female = new ArrayList<>();
        female.add(employee);


        when(employeeRepository.findByGender("Female")).thenReturn(female);; //stub

        //when
        List<Employee> result= employeeService.findByGender("Female");

        //then
        assertEquals(female, result);
        verify(employeeRepository).findByGender("Female");//spy
    }

    @Test
    void should_return_null_and_delete_when_delete_by_gender_given_employees(){
        //given
        Employee employee = new Employee(1,"Susan",22,"Female",10000);

        //when
        Employee result = employeeService.delete(1);

        //then
        assertNull(result);
        verify(employeeRepository).delete(1);//spy
    }

    @Test
    void should_return_employees_by_page_and_pageSize_when_get_by_page_and_pageSize_given_employees(){
        //given
        Employee employee1 = new Employee(1,"Susan",22,"Female",10000);
        Employee employee2 = new Employee(1,"Susan",22,"Female",10000);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        when(employeeRepository.findByPage(1,2)).thenReturn(employees); //stub

        //when
        List<Employee> result= employeeService.findByPage(1,2);

        //then
        assertEquals(employees, result);
        verify(employeeRepository).findByPage(1,2);//spy
    }

    @Test
    void should_return_employees_when_create_given_employees(){
        //given
        Employee employee1 = new Employee(1,"Susan",22,"Female",10000);

        when(employeeRepository.create(employee1)).thenReturn(employee1); //stub

        //when


        //then
        Employee result = employeeService.create(employee1);
        assertEquals(employee1, result);
    }


}

