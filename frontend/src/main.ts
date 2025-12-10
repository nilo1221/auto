import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  // In produzione, disabilita i log di debug
  window.console.log = function() {};
  window.console.warn = function() {};
  window.console.error = function() {};
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
