import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '57945daca0be04289940c31adf7c0eaf0cc505bca6530761465af433e807a14a') {
    pending.push(import('./chunks/chunk-6a83b54dee5ef2fd08d2a301d81aec8522a63ea36b28955e054df6bf37233392.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;