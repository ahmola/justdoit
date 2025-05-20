package com.park.fullstack3backend.todo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Table(name = "todo")
@Entity
public class ToDo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private Boolean completed;

    @Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", completed=" + completed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ToDo)) return false;
        ToDo toDo = (ToDo) o;
        return Objects.equals(getId(), toDo.getId()) && Objects.equals(getName(), toDo.getName()) && Objects.equals(getCompleted(), toDo.getCompleted());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCompleted());
    }
}
