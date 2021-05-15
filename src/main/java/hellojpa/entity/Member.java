package hellojpa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
 @Id //pk와 매핑
 @GeneratedValue//시퀀스 생성
 private Long id;
 @Column(name = "USERNAME", nullable = false, length=20)//가장 많이 사용됨.
 private String name;
 private int age;

 /*@Column(name = "TEAM_ID")
 private Long teamId;*/
    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
