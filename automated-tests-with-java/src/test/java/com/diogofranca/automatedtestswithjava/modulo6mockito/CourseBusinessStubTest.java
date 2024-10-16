package com.diogofranca.automatedtestswithjava.modulo6mockito;

import com.diogofranca.automatedtestswithjava.modulo6mockito.service.CourseBusiness;
import com.diogofranca.automatedtestswithjava.modulo6mockito.service.CourseService;
import com.diogofranca.automatedtestswithjava.modulo6mockito.service.CourseServiceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CourseBusinessStubTest {

	@Test
	void testCoursesRelatedToSpring_WhenUsingAStub(){

		CourseService courseServiceStub = new CourseServiceStub();
		CourseBusiness business = new CourseBusiness(courseServiceStub);

		var filteredCourses = business.retrieveCoursesRelatedToSpring("Diogo");

		Assertions.assertEquals(4, filteredCourses.size());
	}

	@Test
	void testCoursesRelatedToSpring_WhenUsingFooBarStudent(){

		CourseService courseServiceStub = new CourseServiceStub();
		CourseBusiness business = new CourseBusiness(courseServiceStub);

		var filteredCourses = business.retrieveCoursesRelatedToSpring("Foo Bar");

		Assertions.assertEquals(0, filteredCourses.size());
	}
}
