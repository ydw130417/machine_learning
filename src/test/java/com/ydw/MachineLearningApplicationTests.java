package com.ydw;

import com.ydw.model.jpa_model.Machine;
import com.ydw.repository.jap_repository.MachineRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MachineLearningApplicationTests {

	@Autowired
	MachineRepository machineRepository;

	@Test
	public void contextLoads() {
		Machine machine = new Machine("123");
		machineRepository.saveAndFlush(machine);
		machineRepository.findById("123").ifPresent(System.out::println);
		machine.setThirdpic("123");
		machineRepository.saveAndFlush(machine);
		machineRepository.findById("123").ifPresent(System.out::println);
	}

	@Test
	public void testUpdate(){
		Machine machine = new Machine("123");
		machineRepository.save(machine);
		machineRepository.findById("123").ifPresent(System.out::println);
		machine.setFirstpic("123");
		machineRepository.save(machine);
		machineRepository.findById("123").ifPresent(System.out::println);
	}

}
