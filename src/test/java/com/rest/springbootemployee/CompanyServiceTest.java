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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository companyRepository;

    @InjectMocks
    CompanyService companyService;


    @Test
    void should_return_all_companies_when_find_all_given_companies() {
        //given
        List<Company> companies = new ArrayList<>();
        Company summer = companyRepository.create(new Company(101, "summer", new ArrayList<>()));
        given(companyRepository.findAll())
                .willReturn(companies);
        //when
        List<Company> actual = companyService.findAll();
        //then
        assertEquals(companies, actual);
    }

    @Test
    void should_return_a_company_when_get_company_given_company_id() {
        //given
        List<Company> companies = new ArrayList<>();
        Company summer = companyRepository.create(new Company(101, "summer", new ArrayList<>()));



        when(companyRepository.findById(101)).thenReturn(summer); //stub

        //when
        Company result= companyService.findById(101);

        //then
        assertEquals(summer, result);
        verify(companyRepository).findById(101);//spy
    }

    @Test
    void should_return_employees_when_get_company_employee_given_company_id() {
        //given
        List<Employee> employeesOfCompany1 = new ArrayList<>();
        Employee employee1 = new Employee(1,"Susan",22,"Female",10000);
        Employee employee2 = new Employee(1,"Boa",22,"Male",10000);

        when(companyRepository.getEmployees(1)).thenReturn(employeesOfCompany1); //stub

        //when
        List<Employee> result= companyService.getEmployees(1);

        //then
        assertEquals(employeesOfCompany1, result);
        verify(companyRepository).getEmployees(1);//spy
    }


}
