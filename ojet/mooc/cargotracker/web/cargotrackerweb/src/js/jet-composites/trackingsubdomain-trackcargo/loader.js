/**
  Copyright (c) 2015, 2019, Oracle and/or its affiliates.
  The Universal Permissive License (UPL), Version 1.0
*/
define(['ojs/ojcomposite', 'text!./trackingsubdomain-trackcargo-view.html', './trackingsubdomain-trackcargo-viewModel', 'text!./component.json', 'css!./trackingsubdomain-trackcargo-styles'],
  function(Composite, view, viewModel, metadata) {
    Composite.register('trackingsubdomain-trackcargo', {
      view: view,
      viewModel: viewModel,
      metadata: JSON.parse(metadata)
    });
  }
);