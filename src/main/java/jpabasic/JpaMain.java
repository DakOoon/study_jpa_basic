package jpabasic;

import jakarta.persistence.*;
import jpabasic.domain.Member;
import jpabasic.domain.Order;
import jpabasic.domain.RoleType;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setName("hi");
            member.setRoleType(RoleType.USER);
            em.persist(member);

            Order order1 = new Order();
//            order1.setMember(member);
//            member.getOrderList().add(order1);
            order1.changeMember(member);
            em.persist(order1);
            Order order2 = new Order();
//            order2.setMember(member);
//            member.getOrderList().add(order2);
            order2.changeMember(member);
            em.persist(order2);

//            em.flush();
//            em.clear();

            Order findOrder = em.find(Order.class, 1L);
            System.out.println(findOrder.getMember().getId() +" : "+ findOrder.getMember().getName());

            Member findMember = em.find(Member.class, 1L);
            System.out.println(findMember.getOrderList().size());

            System.out.println("commit");
            tx.commit();
        } catch (Exception e) {
            System.out.println("rollback");
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
