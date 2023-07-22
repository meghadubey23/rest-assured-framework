package entity;

import apis.BaseApi;
import apis.libraryapis.GetCoursesPojoResponse;

import java.util.ArrayList;
import java.util.List;

public class LibraryApiEntity extends BaseEntity {
    private final BaseApi actualResponse;
    private final BaseApi expectedResponse;

    public LibraryApiEntity(BaseApi actualResponse, BaseApi expectedResponse) {
        this.actualResponse = actualResponse;
        this.expectedResponse = expectedResponse;
    }

    public void verify() {
        GetCoursesPojoResponse actual = (GetCoursesPojoResponse) actualResponse;
        GetCoursesPojoResponse expected = (GetCoursesPojoResponse) actualResponse;

        List<String[]> list = new ArrayList<>();

        String[] subList = new String[3];
        subList[0] = actual.getInstructor();
        subList[1] = expected.getInstructor();
        subList[2] = "Instructor";
        list.add(subList);

        subList = new String[3];
        subList[0] = actual.getUrl();
        subList[1] = expected.getUrl();
        subList[2] = "Url";
        list.add(subList);

        subList = new String[3];
        subList[0] = actual.getServices();
        subList[1] = expected.getServices();
        subList[2] = "Services";
        list.add(subList);

        subList = new String[3];
        subList[0] = actual.getExpertise();
        subList[1] = expected.getExpertise();
        subList[2] = "Expertise";
        list.add(subList);

        verify(list);

        for (int i = 0; i < actual.getCourses().getWebAutomation().size(); i++) {
            subList = new String[3];
            subList[0] = actual.getCourses().getWebAutomation().get(i).getCourseTitle();
            subList[1] = expected.getCourses().getWebAutomation().get(i).getCourseTitle();
            subList[2] = "courses:webAutomation:courseTitle";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getCourses().getWebAutomation().get(i).getPrice();
            subList[1] = expected.getCourses().getWebAutomation().get(i).getPrice();
            subList[2] = "courses:webAutomation:price";
            list.add(subList);
        }

        for (int i = 0; i < actual.getCourses().getApi().size(); i++) {
            subList = new String[3];
            subList[0] = actual.getCourses().getWebAutomation().get(i).getCourseTitle();
            subList[1] = expected.getCourses().getWebAutomation().get(i).getCourseTitle();
            subList[2] = "courses:webAutomation:courseTitle";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getCourses().getWebAutomation().get(i).getPrice();
            subList[1] = expected.getCourses().getWebAutomation().get(i).getPrice();
            subList[2] = "courses:webAutomation:price";
            list.add(subList);
        }

        for (int i = 0; i < actual.getCourses().getMobile().size(); i++) {
            subList = new String[3];
            subList[0] = actual.getCourses().getWebAutomation().get(i).getCourseTitle();
            subList[1] = expected.getCourses().getWebAutomation().get(i).getCourseTitle();
            subList[2] = "courses:webAutomation:courseTitle";
            list.add(subList);

            subList = new String[3];
            subList[0] = actual.getCourses().getWebAutomation().get(i).getPrice();
            subList[1] = expected.getCourses().getWebAutomation().get(i).getPrice();
            subList[2] = "courses:webAutomation:price";
            list.add(subList);
        }

        subList = new String[3];
        subList[0] = actual.getLinkedIn();
        subList[1] = expected.getLinkedIn();
        subList[2] = "linkedIn";
        list.add(subList);
    }
}
