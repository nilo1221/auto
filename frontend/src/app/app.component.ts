import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <a class="navbar-brand" href="#">Concessionario Auto</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" routerLink="/" routerLinkActive="active">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/veicoli" routerLinkActive="active">Veicoli</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/clienti" routerLinkActive="active">Clienti</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" routerLink="/vendite" routerLinkActive="active">Vendite</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container mt-4">
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .navbar { margin-bottom: 20px; }
    .router-link-active { font-weight: bold; }
  `]
})
export class AppComponent {
  title = 'Concessionario Auto';
}
