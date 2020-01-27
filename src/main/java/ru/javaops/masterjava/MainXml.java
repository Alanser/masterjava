package ru.javaops.masterjava;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.*;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainXml {
    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    public static void main(String[] args) throws Exception {
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
        Payload payload = JAXB_PARSER.unmarshal(Resources.getResource("payload.xml").openStream());

        List<User> allUsers = payload.getUsers().getUser();
        List<Group> groups = payload.getProjects().getProject().stream()
                .filter(p -> args[0].equals(p.getName()))
                .flatMap(p -> p.getGroups().getGroup().stream())
                .collect(Collectors.toList());
        List<UserGroup.Members.Member> members = payload.getUserGroups().getUserGroup().stream()
                .filter(ug -> groups.contains(ug.getGroup()))
                .flatMap(ug -> ug.getMembers().getMember().stream())
                .collect(Collectors.toList());
        members.stream()
                .map(member -> allUsers.stream().filter(u -> member.getUser().equals(u.getEmail())).findFirst().orElse(null))
                .sorted(Comparator.comparing(User::getFullName))
                .forEach(u -> System.out.println(u.getFullName() + " - " + u.getEmail()));
    }
}
