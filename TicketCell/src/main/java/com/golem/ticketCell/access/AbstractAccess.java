package com.golem.ticketCell.access;

import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public abstract class AbstractAccess implements CollectionAccess{

    public final int priority;

    public AbstractAccess () {
        this.priority = 1;
    }
    public AbstractAccess (int priority) {
        this.priority = priority;
    }

    static class AccessComparator implements Comparator<AbstractAccess> {
        @Override
        public int compare(AbstractAccess o1, AbstractAccess o2) {
            return o1.priority - o2.priority;
        }
    }

    public static List<AbstractAccess> getCollectionAccess(ModuleLayer layer) {
        List<AbstractAccess> list = ServiceLoader
                .load(layer, AbstractAccess.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
        list.sort(new AccessComparator());
        return list;
    }
}
