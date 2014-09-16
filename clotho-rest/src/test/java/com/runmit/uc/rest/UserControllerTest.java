package com.runmit.uc.rest;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring-context-test.xml"})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class UserControllerTest
{
//
    private static final String BASE_URL = "http://localhost:8080/spring3-rest-sample/people";
//
//    private Logger log = LoggerFactory.getLogger(RestClientTest.class);
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Test
//    public void saveAndGet() throws Exception{
//        // save
//        Person input = new Person("jane","doe","jane.doe","pw",Person.RoleLevel.ADMIN.getLevel());
//        assertNull(input.getId());
//        Person output = restTemplate.postForObject(BASE_URL, input, Person.class, new Object[]{});
//        assertNotNull("no person",output);
//        assertNotNull(output.getId());
//        assertEquals(input.getUsername(), output.getUsername());
//        log.info("Saved jane.doe with id "+output.getId());
//        // get all
//        People people = restTemplate.getForObject(BASE_URL, People.class,new Object[]{});
//        assertNotNull("no people",people);
//        assertNotNull("no persons in people",people.getPerson());
//        assertTrue("empty persons in people",!people.getPerson().isEmpty());
//        assertEquals("no one person in people",input.getUsername(),people.getPerson().get(0).getUsername());
//        log.info("Peple size "+people.getPerson().size());
//        // get id
//        Map vars = Collections.singletonMap("id", output.getId()+"");
//        Person idPerson = restTemplate.getForObject(BASE_URL+"/person/{id}", Person.class,vars);
//        assertNotNull("no person",idPerson);
//        assertNotNull(idPerson.getId());
//        assertEquals(input.getUsername(), idPerson.getUsername());
//        log.info("Get person by id <"+output.getId()+"> : "+idPerson.getUsername());
//    }

}
