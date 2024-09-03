package com.dh.clinica;


//class OdontologoServiceTest {
//
//    public static final Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);
//    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
//
//
//    @BeforeAll
//    static void tablas(){
//        H2Connection.crearTablas();
//    }
//
//    @Test
//    @DisplayName("Testear que se guarde un odontologo en la db")
//    void caso1(){
//        Odontologo odontologo = new Odontologo(569, "SEBASTIAN","MENDOZA");
//        Odontologo odontologoDesdeDB = odontologoService.guardarOdontologo(odontologo);
//
//        assertNotNull(odontologoDesdeDB.getId());
//    }
//
//    @Test
//    @DisplayName("Testear que nos muestre todos los odontologos guardados")
//    void caso2(){
//        List<Odontologo> odontologos = new ArrayList<>();
//        odontologos = odontologoService.busarTodos();
//        assertFalse(odontologos.isEmpty());
//    }
//
//    @Test
//    @DisplayName("Testear que nos busque un odontologo por id")
//    void caso3(){
//        Integer id = 1;
//        odontologoService.buscarPorId(Integer.valueOf(id.toString()));
//        assertEquals(id, odontologoService.buscarPorId(id).getId());
//    }
//
//}







