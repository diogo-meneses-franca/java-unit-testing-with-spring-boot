package com.diogofranca.automatedtestswithjava.modulo6mockito;

import com.diogofranca.automatedtestswithjava.modulo6mockito.service.CourseBusiness;
import com.diogofranca.automatedtestswithjava.modulo6mockito.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class CourseBusinessMockWithBDDTest {

	CourseService mockService;
	CourseBusiness business;
	List<String> courses;

	@BeforeEach
	void setup(){
		mockService = mock(CourseService.class);
		business = new CourseBusiness(mockService);
		courses = Arrays.asList(
				"REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
				"Agile Desmistificado com Scrum, XP, Kanban e Trello",
				"Spotify Engineering Culture Desmistificado",
				"REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
				"Docker do Zero à Maestria - Contêinerização Desmistificada",
				"Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
				"Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
				"Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
				"REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
				"Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
				"Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
		);
	}

	@Test
	void testCoursesRelatedToSpring_WhenUsingMock(){

		/*
		Para utilizar os conceitos do BDD (Behavior Driven Development) foi trocado o
		when() por given() e ao invés de thenReturn() utiliza-se willReturn(). Esses métodos de testes
		são do pacote BDDMockito
		 */
		given(mockService.retrieveCourses("Diogo")).willReturn(courses);
		var filteredCourses = business.retrieveCoursesRelatedToSpring("Diogo");

		/*Ao invés de AssertEquals() utiliza-se AssertThat() do Hamcrest e is(4)
		Percebe-se que ao ler o método a escrita fica inteligível e nota-se que a
		ação vem antes do valor esperado, ao contrario do assertEquals()
		 */
		assertThat(filteredCourses.size(), is(4));
	}

	@Test
	void testDeleteCoursesNotRelatedToSrping_WhenUsingMockitoVerify(){
		given(mockService.retrieveCourses("Diogo")).willReturn(courses);
		business.deleteCoursesNotRelatedToSpring("Diogo");
		verify(mockService).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		//verifica se o método será chamado apenas 1 vez
		verify(mockService, times(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		//verifica se o método será chamado ao menos 1 vez
		verify(mockService, atLeast(1)).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService, atLeastOnce()).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		//Neste caso verifica se nunca (never()) será excluído um curso q contem "Spring" no nome.
		verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
	}

	/*
	Neste exemplo o then().should() desempenha o mesmo papel do verify, que serve para
	testar a chamada de métodos que não possuem retorno  (void).
	 */
	@Test
	void testDeleteCoursesNotRelatedToSrping_WhenUsingMockitoThenAndShould(){
		given(mockService.retrieveCourses("Diogo")).willReturn(courses);
		business.deleteCoursesNotRelatedToSpring("Diogo");
		then(mockService)
				.should()
				.deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");

		//verifica se o método será chamado apenas 1 vez
		then(mockService)
				.should(times(1))
				.deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
	}

	/*
	Testa se o método deleteCourses() será invocado contra um argumento especifíco,
	no caso o curso "Agile Desmistificado com Scrum, XP, Kanban e Trello".
	Desse modo é possível testar vários ou até todos os argumentos de uma estrutura de dados.
	 */
	@Test
	void testDeleteCoursesNotRelatedToSrping_WhenUsingArgumentCapture(){
		var courses = Arrays.asList(
				"Agile Desmistificado com Scrum, XP, Kanban e Trello"
		);
		given(mockService.retrieveCourses("Diogo")).willReturn(courses);
		business.deleteCoursesNotRelatedToSpring("Diogo");

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

		then(mockService).should().deleteCourse(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is(agileCourse));
	}

	/*
	Nesse caso o teste captura e testa o método deleteCourse com todos os argumentos
	do stup courses que são 11, mas certifica que o método será chamado apenas 7 vezes pois há
	apenas 7 cursos relacionados ao Spring na lista.
	 */
	@Test
	void testDeleteCoursesNotRelatedToSrping_WhenUsingArgumentCaptureWithAllValues(){
		given(mockService.retrieveCourses("Diogo")).willReturn(courses);
		business.deleteCoursesNotRelatedToSpring("Diogo");

		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		then(mockService).should(times(7)).deleteCourse(argumentCaptor.capture());
		assertThat(argumentCaptor.getAllValues().size(), is(7));
	}

}
