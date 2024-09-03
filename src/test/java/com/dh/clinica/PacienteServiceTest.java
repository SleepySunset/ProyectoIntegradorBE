package com.dh.clinica;

//class PacienteServiceTest {
//    static Logger logger = LoggerFactory.getLogger(PacienteServiceTest.class);
//    PacienteService pacienteService = new PacienteService(new PacienteDaoH2());
//
//    @BeforeAll
//    static void tablas(){
//        H2Connection.crearTablas();
//    }
//
//    @Test
//    @DisplayName("Testear que un paciente se guarde en la base de datos con su domicilio")
//    void caso1(){
//        //Dado
//        Paciente paciente = new Paciente("Romero", "Luciana", "443343", LocalDate.of(2024, 05, 03),
//                new Domicilio("Falsa", 456, "Cipolleti", "Rio Negro"));
//
//        //Cuando
//        Paciente pacienteDesdeDB = pacienteService.guardarPaciente(paciente);
//
//        //Entonces
//        assertNotNull((pacienteDesdeDB.getId()));
//    }
//    @Test
//    @DisplayName("")
//    void caso2(){
//        //Dado
//        Integer id = 1;
//        //Cuando
//        Paciente paciente = pacienteService.buscarPorId(id);
//        //Entonces
//        assertEquals(id, paciente.getId());
//    }
//
//
//}