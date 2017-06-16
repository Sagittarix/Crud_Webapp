package crudwebapp.repository.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import crudwebapp.model.generic.GenericEntity;

/**
 * Created by arnoldas on 17.6.16.
 */
public abstract class GenericRepository<T extends GenericEntity> { //nurodome kad klase dirbs
// su kitomis klasemis kurios yra "bet koks tipas kuris extendina GenericEntity"

    @Autowired
    private EntityManager em;
    private Class<T> type;

    public GenericRepository(Class<T> type) {
        this.type = type;
    }

    @Transactional
    public T createOrUpdate(T t) {
        if(t.getId() != null && find(t.getId()) != null) {
            return em.merge(t);
        } else {
            em.persist(t);
            return t;
        }
    }

    @Transactional
    public T delete(Long id) {
        T t = em.find(type, id);
        em.remove(t);
        return t;
    }

    public T find(Long id) {
        return em.find(type, id);
    }

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);

        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.isNotNull(root.get("id")));

        final TypedQuery<T> query = em.createQuery(criteriaQuery);
        return query.getResultList();

//    private static final String FIND_ALL = "SELECT x FROM Human x";
//    return em.createQuery(FIND_ALL).getResultList();
    }


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