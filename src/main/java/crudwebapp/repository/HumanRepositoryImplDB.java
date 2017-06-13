package crudwebapp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crudwebapp.model.Car;
import crudwebapp.model.Human;

/**
 * Created by arnoldas on 17.6.12.
 */


@Repository
public class HumanRepositoryImplDB implements HumanRepository {
//    private static final String FIND_ALL = "SELECT x FROM Human x";
//    return em.createQuery(FIND_ALL).getResultList();

    @Autowired
    private EntityManager em;

    @Override
    public List<Human> findAllHumans() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Human> criteriaQuery = criteriaBuilder.createQuery(Human.class);
        Root<Human> humanRoot = criteriaQuery.from(Human.class);

        criteriaQuery.select(humanRoot);
        criteriaQuery.where(criteriaBuilder.isNotNull(humanRoot.get("id")));

        final TypedQuery<Human> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Human findHumanById(Long id) {
        Human human = em.find(Human.class, id);
        if (human != null) {
            return human;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void createOrUpdateHuman(Human human) {
        if (human.getId() == null) {

//            Car car = new Car();
//            car.setPlateNumber("LTU666");
//            List<Car> carList = new ArrayList<>();
//            carList.add(car);
//
//            human.setOwnedCars(carList);


            em.persist(human);
        } else {
            Human merged = em.merge(human);
            em.persist(merged);
        }
    }

    @Override
    @Transactional
    public void deleteHuman(Long id) {
        Human human = em.find(Human.class, id);
        em.remove(human);
    }

/*
@Override
public List<EinvoiceContract> getEinvoiceContractsByFilter(
                                            RegisterEinvoiceFilter registerEinvoiceFilter) {
    Date now = dateFromFilter(registerEinvoiceFilter);

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<EinvoiceContract> criteriaQuery = criteriaBuilder
                                                        .createQuery(EinvoiceContract.class);
    Root<EinvoiceContract> einvoiceRoot = criteriaQuery.from(EinvoiceContract.class);
    criteriaQuery.select(einvoiceRoot);
    criteriaQuery.where(
            criteriaBuilder.and(
                    criteriaBuilder.equal(einvoiceRoot.get("status"),
                                          registerEinvoiceFilter.getStatus()),
                    criteriaBuilder.or(
                            criteriaBuilder.isNull(einvoiceRoot.get("sentToTipCounter")),
                            criteriaBuilder.lessThanOrEqualTo(einvoiceRoot
                                                                      .get("sentToTipCounter"),
                                    registerEinvoiceFilter.getNumberTimesToSend())
                    ),
                    criteriaBuilder.or(
                            criteriaBuilder.isNull(einvoiceRoot.get("details")),
                            criteriaBuilder.and(
                                    criteriaBuilder.notLike(einvoiceRoot.get("details"),
                                            "%" + registerEinvoiceFilter
                                                    .getErrorToFilterOne() + "%"),
                                    criteriaBuilder.notLike(einvoiceRoot.get("details"),
                                            "%" + registerEinvoiceFilter
                                                    .getErrorToFilterTwo() + "%")
                            )
                    ),
                    criteriaBuilder.or(
                            criteriaBuilder.isNull(einvoiceRoot.get("sentToTipDate")),
                            criteriaBuilder.lessThan(einvoiceRoot.get("sentToTipDate"), now)
                    )
            )
    );


    criteriaQuery.orderBy(toOrders(registerEinvoiceFilter.getPageRequest().getSort(),
                                   einvoiceRoot,
                                   criteriaBuilder));

    final TypedQuery<EinvoiceContract> query = entityManager
            .createQuery(criteriaQuery)
            .setFirstResult(registerEinvoiceFilter.getPageRequest().getPageNumber())
            .setMaxResults(registerEinvoiceFilter.getPageRequest().getPageSize());
    return query.getResultList();
}

private Date dateFromFilter(RegisterEinvoiceFilter registerEinvoiceFilter) {
    ZoneOffset zoneOffset = systemDefault().getRules().getOffset(now());
    return Date.from((registerEinvoiceFilter.getNow().minusHours(registerEinvoiceFilter
                                                         .getNumberOfHoursBeforeNextRepeat()))
                             .toInstant(zoneOffset));
}
*/






}

