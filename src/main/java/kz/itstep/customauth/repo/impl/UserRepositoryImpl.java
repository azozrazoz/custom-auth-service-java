package kz.itstep.customauth.repo.impl;

import kz.itstep.customauth.data.UserData;
import kz.itstep.customauth.exception.UserException;
import kz.itstep.customauth.model.User;
import kz.itstep.customauth.repo.UserRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws UserException {
        if (login.isEmpty() || password.isEmpty()) {
            throw new UserException("Login or password is empty");
        }

        return Optional.ofNullable(getByLoginAndPassword(login, password)
                .orElseThrow(() -> new UserException("Login or password is empty")));
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws UserException {
        if (login.isEmpty()) {
            throw new UserException("Login is empty");
        }

        return Optional.ofNullable(getByLogin(login)
                .orElseThrow(() -> new UserException("Login is empty")));
    }

    @Override
    public void saveUser(User user) throws UserException {
        if(UserData.users.stream().anyMatch(s -> s.getId().equals(user.getId()) )){
            throw new UserException("Student is already exists");
        }
        UserData.users.add(user);
    }

    private Optional<User> getByLoginAndPassword(String login, String password) {
        return UserData.users.stream()
                .filter(l->l.getLogin().equals(login) && l.getPassword().equals(password)).findFirst();
    }

    private Optional<User> getByLogin(String login) {
        return UserData.users.stream()
                .filter(l->l.getLogin().equals(login)).findFirst();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public User getOne(Long aLong) {
        return null;
    }

    @Override
    public User getById(Long aLong) {
        return null;
    }

    @Override
    public User getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends User> S save(S entity) {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends User> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<User> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return null;
    }
}
