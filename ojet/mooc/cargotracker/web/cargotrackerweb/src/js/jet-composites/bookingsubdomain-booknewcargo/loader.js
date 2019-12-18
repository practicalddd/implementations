/**
  Copyright (c) 2015, 2019, Oracle and/or its affiliates.
  The Universal Permissive License (UPL), Version 1.0
*/
define(['ojs/ojcomposite', 'text!./bookingsubdomain-booknewcargo-view.html', './bookingsubdomain-booknewcargo-viewModel', 'text!./component.json', 'css!./bookingsubdomain-booknewcargo-styles'],
  function(Composite, view, viewModel, metadata) {
    Composite.register('bookingsubdomain-booknewcargo', {
      view: view,
      viewModel: viewModel,
      metadata: JSON.parse(metadata)
    });
  }
);