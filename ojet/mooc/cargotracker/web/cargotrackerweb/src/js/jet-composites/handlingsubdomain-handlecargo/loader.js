/**
  Copyright (c) 2015, 2019, Oracle and/or its affiliates.
  The Universal Permissive License (UPL), Version 1.0
*/
define(['ojs/ojcomposite', 'text!./handlingsubdomain-handlecargo-view.html', './handlingsubdomain-handlecargo-viewModel', 'text!./component.json', 'css!./handlingsubdomain-handlecargo-styles'],
  function(Composite, view, viewModel, metadata) {
    Composite.register('handlingsubdomain-handlecargo', {
      view: view,
      viewModel: viewModel,
      metadata: JSON.parse(metadata)
    });
  }
);