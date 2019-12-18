/**
  Copyright (c) 2015, 2019, Oracle and/or its affiliates.
  The Universal Permissive License (UPL), Version 1.0
*/
define(['ojs/ojcomposite', 'text!./routingsubdomain-routecargo-view.html', './routingsubdomain-routecargo-viewModel', 'text!./component.json', 'css!./routingsubdomain-routecargo-styles'],
  function(Composite, view, viewModel, metadata) {
    Composite.register('routingsubdomain-routecargo', {
      view: view,
      viewModel: viewModel,
      metadata: JSON.parse(metadata)
    });
  }
);