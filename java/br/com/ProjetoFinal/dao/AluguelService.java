package br.com.ProjetoFinal.dao;

import br.com.ProjetoFinal.model.Aluguel;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AluguelService {

    public Aluguel salvar(Aluguel a){
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            tx = s.beginTransaction();
            s.persist(a);
            tx.commit();
            return a;
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public Aluguel atualizar(Aluguel a){
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            tx = s.beginTransaction();
            s.merge(a);
            tx.commit();
            return a;
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public Aluguel buscarPorId(int id){
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            return s.get(Aluguel.class, (long) id);
        }
    }

    public void excluir(int id){
        Transaction tx = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            tx = s.beginTransaction();
            Aluguel a = s.get(Aluguel.class, (long) id);
            if(a != null) s.remove(a);
            tx.commit();
        } catch(Exception e){
            if(tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Aluguel> listarTodos(){
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            return s.createQuery("from Aluguel", Aluguel.class).list();
        }
    }
}
