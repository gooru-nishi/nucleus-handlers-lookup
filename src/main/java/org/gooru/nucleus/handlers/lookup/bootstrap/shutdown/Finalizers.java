package org.gooru.nucleus.handlers.lookup.bootstrap.shutdown;

import org.gooru.nucleus.handlers.lookup.app.components.DataSourceRegistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Finalizers implements Iterable<Finalizer> {


  private final Iterator<Finalizer> internalIterator;

  public Finalizers() {
    List<Finalizer> finalizers = new ArrayList<>();
    finalizers.add(DataSourceRegistry.getInstance());
    internalIterator = finalizers.iterator();
  }

  @Override
  public Iterator<Finalizer> iterator() {
    return new Iterator<Finalizer>() {

      @Override
      public boolean hasNext() {
        return internalIterator.hasNext();
      }

      @Override
      public Finalizer next() {
        return internalIterator.next();
      }

    };
  }


}
