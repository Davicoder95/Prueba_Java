import com.riwi.controllers.CourseController;
import com.riwi.controllers.InscriptionController;
import com.riwi.controllers.QualificationController;
import com.riwi.controllers.StudentController;
import com.riwi.entities.CourseEntity;
import com.riwi.entities.InscriptionEntity;
import com.riwi.entities.QualificationEntity;
import com.riwi.entities.StudentEntity;
import enums.EnumStatus;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
// se crea el menu interactivo
        String opcionPrincipal;

        do {opcionPrincipal = JOptionPane.showInputDialog("Welcome to RiwiAcademy \n" +
                "Options\n" +
                "1. Enter the Student interface\n" +
                "2. Enter the Courses interface\n" +
                "3. Enter the Qualification interface\n" +
                "4. Enter the Inscription interface\n " +
                "5. Exit");
            switch (opcionPrincipal){
                case "1":
                    // se instancia el controlador y los atributos de la entidad
                    StudentController studentController= new StudentController();
                    String name;
                    String lastName;
                    String email;
                    EnumStatus status;
                    Integer document;
                    Integer id;
                    String opcion1;
                    do {
                        opcion1 = JOptionPane.showInputDialog("" +
                                "Bienvenidos  \n" +
                                "Opciones: \n" +
                                "1. Create Student \n" +
                                "2. Delete Student\n" +
                                "3. SearchStudent \n" +
                                "4. Update Student \n" +
                                "5. List students with active status \n"+
                                "6. Search for email \n" +
                                "7. Exit"
                        );

                        switch (opcion1) {
                            case "1" -> {
                                // Ingresa los datos para crear el estudiante
                                name= JOptionPane.showInputDialog("Enter  name");
                                lastName= JOptionPane.showInputDialog("Enter lastname");
                                email= JOptionPane.showInputDialog("enter email");
                                status = (EnumStatus) (JOptionPane.showInputDialog(
                                        null,
                                        "Choose your state",
                                        "",
                                        JOptionPane.QUESTION_MESSAGE,// muestra las opciones del enum como un select
                                        null,
                                        EnumStatus.values(),
                                        EnumStatus.values()
                                ));
                                document = Integer.parseInt(JOptionPane.showInputDialog("Enter document"));

                                // se almacenan los datos ya creados para mostrarlos en pantalla
                                Object resultado = studentController.create(name,lastName,email,status,document);

                                JOptionPane.showMessageDialog(null, resultado.toString());

                            }
                            case "2" -> {
                                // se pide el id para eliminar el estudiante
                                id = Integer.parseInt(JOptionPane.showInputDialog("Insert id Student"));
                                boolean estado = studentController.delete(id);
                                // se hace un if para verificar que el id exista
                                if (!estado){
                                    JOptionPane.showMessageDialog(null, "Student deleted");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error ");
                                }
                            }
                            case "3" -> {
                                // se pide el id del estudiante para mostrar todos sus datos
                                id = Integer.parseInt(JOptionPane.showInputDialog("Insert id  Student"));
                                Object leer =studentController.read(id);
                                JOptionPane.showMessageDialog(null, leer.toString());
                            }
                            case "4" -> {
                                // se solicita todos los datos de estudiante ya que se hara una modificacion
                                id= Integer.parseInt(JOptionPane.showInputDialog("insert id"));
                                name= JOptionPane.showInputDialog("Enter name");
                                lastName= JOptionPane.showInputDialog("Enter lastname");
                                email= JOptionPane.showInputDialog("Enter email");
                                status = (EnumStatus) (JOptionPane.showInputDialog(
                                        null,
                                        "Chosse your state",
                                        "",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        EnumStatus.values(),
                                        EnumStatus.values()
                                ));
                                document = Integer.parseInt(JOptionPane.showInputDialog("Enter document"));

                                // se crea un nuevo estudiante con los datos modificados
                                StudentEntity studentEntity = new StudentEntity(name,lastName,email,status,document);
                                studentController.update(studentEntity, id);
                                JOptionPane.showMessageDialog(null, studentEntity.toString());


                            }
                            case "5" ->{
                                // se lista los estudiantes activos por paginacion
                                String pageSize = JOptionPane.showInputDialog("How many users per page?");
                                int numberPage = 1;

                                String confirm;
                                do {
                                    List<StudentEntity> userList =studentController.readAll(Integer.parseInt(pageSize), numberPage);
                                    if (userList.isEmpty()){
                                        numberPage--; // no hay mas informacion
                                    }
                                    confirm = JOptionPane.showInputDialog(null, "Page: "+ numberPage + "\n" + userList
                                            + "\n" + "prev or next");//se crean  elecciones para pasar o devolver pagina

                                    if (confirm.equalsIgnoreCase("prev")){
                                        numberPage--;

                                        if (numberPage<1){
                                            numberPage = 1;
                                        }
                                    } else if (confirm.equalsIgnoreCase("next")) {
                                        numberPage++;
                                    }

                                }while(confirm.equalsIgnoreCase("next") || confirm.equalsIgnoreCase("prev"));//ignoramos mayusculas o minusculas
                            }
                            case "6"->{
                                // se busca por email de usuario, ignorando el case
                                email= JOptionPane.showInputDialog("Enter email of Student");
                                Object leer =studentController.readEmail(email);
                                JOptionPane.showMessageDialog(null, leer.toString());// se muestran las coinsidencias a la busqueda
                            }

                        }

                    }
                    while (!opcion1.equals("7"));
                    break;
                case "2":
                    // se instancia el controlador y los atributos de la entidad
                    CourseController courseController = new CourseController();

                    int idCourse;
                    String nameCourse;
                    String opcion2;
                    do {
                        opcion2 = JOptionPane.showInputDialog("" +
                                "Welcome  \n" +
                                "Options: \n" +
                                "1. Create course \n" +
                                "2. Delete course\n" +
                                "3. Search course \n" +
                                "4. Update course \n" +
                                "5. List course \n" +
                                "6. Exit\n"
                        );

                        switch (opcion2) {
                            case "1" -> {
                                // Input of name
                                nameCourse= JOptionPane.showInputDialog("Enter the course name");
                                Object resultado = courseController.create(nameCourse);

                                JOptionPane.showMessageDialog(null, resultado.toString());

                            }
                            case "2" -> {
                                idCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter course id"));

                                boolean estado = courseController.delete(idCourse);

                                if (!estado){
                                    JOptionPane.showMessageDialog(null, "Course deleted ");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error  ");
                                }
                            }
                            case "3" -> {
                                idCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter course id"));
                                Object leer =courseController.read(idCourse);
                                JOptionPane.showMessageDialog(null, leer.toString());
                            }
                            case "4" -> {
                                idCourse= Integer.parseInt(JOptionPane.showInputDialog("Enter id"));
                                nameCourse= JOptionPane.showInputDialog("Enter the course name ");

                                CourseEntity courseEntity= new CourseEntity(nameCourse);
                                courseController.update(courseEntity, idCourse);
                                JOptionPane.showMessageDialog(null, courseEntity.toString());
                            }
                            case "5" ->{
                                String pageSize = JOptionPane.showInputDialog("How many users per page?");
                                int numberPage = 1;

                                String confirm;
                                do {
                                    List<CourseEntity> userList =courseController.readAll(Integer.parseInt(pageSize), numberPage);
                                    if (userList.isEmpty()){
                                        numberPage--; // not more info
                                    }
                                    confirm = JOptionPane.showInputDialog(null, "Page: "+ numberPage + "\n" + userList
                                            + "\n" + "prev or next");

                                    if (confirm.equalsIgnoreCase("prev")){
                                        numberPage--;

                                        if (numberPage<1){
                                            numberPage = 1;
                                        }
                                    } else if (confirm.equalsIgnoreCase("next")) {
                                        numberPage++;
                                    }

                                }while(confirm.equalsIgnoreCase("next") || confirm.equalsIgnoreCase("prev"));
                            }
                        }
                    }
                    while (!opcion2.equals("6"));
                    break;
                case "3":
                    QualificationController qualificationController= new QualificationController();
                    String description;
                    Integer qualification;
                    Integer id_course;
                    Integer idQualification;
                    Integer idStudent;
                    String opcion4;

                    do {
                        opcion4 = JOptionPane.showInputDialog("" +
                                "Welcome \n" +
                                "Options: \n" +
                                "1. Create quialification \n" +
                                "2. Delete quialification\n" +
                                "3. Search quialification \n" +
                                "4. Update  quialification \n" +
                                "5. Exit \n"
                        );

                        switch (opcion4) {
                            case "1" -> {
                                // Input of name
                                description= JOptionPane.showInputDialog("Enter description of qualification");
                                qualification= Integer.parseInt(JOptionPane.showInputDialog("Enter qualification"));
                                if (qualification>100){
                                    JOptionPane.showMessageDialog(null,"the grade must be from 0 to 100");
                                    break;
                                }
                                id_course = Integer.parseInt(JOptionPane.showInputDialog("Enter course id"));
                                idStudent= Integer.parseInt(JOptionPane.showInputDialog("Enter student id"));

                                Object resultado = qualificationController.create(description,qualification,id_course,idStudent);

                                JOptionPane.showMessageDialog(null, resultado.toString());

                            }
                            case "2" -> {
                                idQualification = Integer.parseInt(JOptionPane.showInputDialog("Inserte el ID de la qualification"));
                                boolean estado = qualificationController.delete(idQualification);

                                if (!estado){
                                    JOptionPane.showMessageDialog(null, "Qualification deleted");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error  ");
                                }
                            }
                            case "3" -> {
                                idQualification= Integer.parseInt(JOptionPane.showInputDialog("Enter id Qualification"));
                                Object leer =qualificationController.read(idQualification);
                                JOptionPane.showMessageDialog(null, leer.toString());
                            }
                            case "4" -> {
                                idQualification= Integer.parseInt(JOptionPane.showInputDialog("Enter  id "));
                                description= JOptionPane.showInputDialog("Enter description of the  qualification");
                                qualification= Integer.parseInt(JOptionPane.showInputDialog("Enter qualification"));
                                if (qualification<100){
                                    JOptionPane.showMessageDialog(null,"the grade must be from 0 to 100");
                                    break;
                                }
                                id_course = Integer.parseInt(JOptionPane.showInputDialog("Enter course id"));
                                idStudent= Integer.parseInt(JOptionPane.showInputDialog("Enter student id"));

                                QualificationEntity qualificationEntity= new QualificationEntity(description,qualification,id_course,idStudent);
                                qualificationController.update(qualificationEntity, idQualification);
                                JOptionPane.showMessageDialog(null, qualificationEntity.toString());


                            }
                        }
                    }
                    while (!opcion4.equals("5"));
                    break;
                case "4":
                    InscriptionController inscriptionController= new InscriptionController();
                    int id_Student;
                    int id_Course;
                    int idInscription;

                    String opcion3;
                    do {
                        opcion3 = JOptionPane.showInputDialog("" +
                                "Welcome  \n" +
                                "Options: \n" +
                                "1. Create incription \n" +
                                "2. Delete incription\n" +
                                "3. Search incription \n" +
                                "4. Update incription \n" +
                                "5. Salir \n"
                        );

                        switch (opcion3) {
                            case "1" -> {
                                // Input of name
                                id_Student= Integer.parseInt(JOptionPane.showInputDialog("Enter student id"));
                                id_Course = Integer.parseInt(JOptionPane.showInputDialog("Enter course id"));
                                // Input of age

                                Object resultado = inscriptionController.create(id_Student,id_Course);

                                JOptionPane.showMessageDialog(null, resultado.toString());

                            }
                            case "2" -> {
                                idInscription = Integer.parseInt(JOptionPane.showInputDialog("Enter inscription id"));
                                JOptionPane.showMessageDialog(null,"Warning, if you delete this registration the note history will be deleted");
                                boolean estado = inscriptionController.delete(idInscription);

                                if (!estado){
                                    JOptionPane.showMessageDialog(null, "Inscription deleted");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error ");
                                }
                            }
                            case "3" -> {
                                idInscription= Integer.parseInt(JOptionPane.showInputDialog("Enter inscription id"));
                                Object leer =inscriptionController.read(idInscription);
                                JOptionPane.showMessageDialog(null, leer.toString());
                            }
                            case "4" -> {
                                idInscription= Integer.parseInt(JOptionPane.showInputDialog("Enter inscription id "));
                                idStudent= Integer.parseInt(JOptionPane.showInputDialog("Enter student id"));
                                idCourse = Integer.parseInt(JOptionPane.showInputDialog("Enter course id"));

                                InscriptionEntity inscriptionEntity= new InscriptionEntity(idStudent,idCourse);
                                inscriptionController.update(inscriptionEntity, idInscription);
                                JOptionPane.showMessageDialog(null, inscriptionEntity.toString());


                            }
                        }
                    }
                    while (!opcion3.equals("5"));
            }


        }while (!opcionPrincipal.equals("5"));


        

    }
}