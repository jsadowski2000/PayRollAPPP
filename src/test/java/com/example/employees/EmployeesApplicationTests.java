package com.example.employees;

import com.example.employees.model.AccountInfo;
import com.example.employees.model.Contract;
import com.example.employees.model.Employee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
@Transactional
class EmployeesApplicationTests {
	@Test
	public void createAndPrintEmployeeWithContractAndAccountInfo() {
		// Tworzymy obiekt klasy Employee
		Employee employee = new Employee();
		employee.setName("John");
		employee.setSurname("Doe");
		employee.setEmail("john.doe@example.com");
		// Ustawiamy datę utworzenia
		employee.setCreateDate(new Date());

		// Ustawiamy datę ostatniej aktualizacji
		employee.setLastUpdate(LocalDateTime.now());

		// Tworzymy obiekt klasy Contract
		Contract contract = new Contract();
		contract.setContractType("Full Time");
		contract.setSalary(50000.0);
		contract.setContractDate(new Date());
		contract.setValidityOfContract(new Date());

		// Tworzymy obiekt klasy AccountInfo
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountType("Savings");
		accountInfo.setAccountNumber(12345);

		// Ustawiamy relacje między obiektami
		employee.getContracts().add(contract);
		contract.setEmployee(employee);

		employee.getAccountsInfo().add(accountInfo);
		accountInfo.setEmployee(employee);

		// Wypisujemy informacje o pracowniku, umowie i informacjach o koncie w konsoli
		System.out.println("Employee Details:");
		System.out.println("Name: " + employee.getName());
		System.out.println("Surname: " + employee.getSurname());
		System.out.println("Email: " + employee.getEmail());
		System.out.println("Created Date: " + employee.getCreateDate());
		System.out.println("Last Update: " + employee.getLastUpdate());

		System.out.println("\nContract Details:");
		System.out.println("Type: " + contract.getContractType());
		System.out.println("Salary: " + contract.getSalary());
		System.out.println("Contract Date: " + contract.getContractDate());
		System.out.println("Validity: " + contract.getValidityOfContract());

		System.out.println("\nAccountInfo Details:");
		System.out.println("Type: " + accountInfo.getAccountType());
		System.out.println("Account Number: " + accountInfo.getAccountNumber());
	}
}