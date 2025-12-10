import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  template: `
    <div class="jumbotron">
      <h1 class="display-4">Benvenuto nel Concessionario Auto</h1>
      <p class="lead">Scopri la nostra selezione di veicoli di alta qualità.</p>
      <hr class="my-4">
      <p>Naviga tra le nostre sezioni per visualizzare i veicoli disponibili, i clienti e le vendite.</p>
      <a class="btn btn-primary btn-lg" href="#" role="button">Scopri di più</a>
    </div>

    <div class="row mt-5">
      <div class="col-md-4 mb-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Veicoli</h5>
            <p class="card-text">Visualizza la nostra gamma di automobili, motociclette e furgoni disponibili.</p>
            <a routerLink="/veicoli" class="btn btn-outline-primary">Vedi veicoli</a>
          </div>
        </div>
      </div>
      <div class="col-md-4 mb-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Clienti</h5>
            <p class="card-text">Gestisci i clienti del concessionario.</p>
            <a routerLink="/clienti" class="btn btn-outline-primary">Vedi clienti</a>
          </div>
        </div>
      </div>
      <div class="col-md-4 mb-4">
        <div class="card">
          <div class="card-body">
            <h5 class="card-title">Vendite</h5>
            <p class="card-text">Visualizza e gestisci le vendite effettuate.</p>
            <a routerLink="/vendite" class="btn btn-outline-primary">Vedi vendite</a>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .jumbotron {
      background-color: #f8f9fa;
      padding: 2rem 1rem;
      margin-bottom: 2rem;
      border-radius: 0.3rem;
    }
    .card {
      height: 100%;
      transition: transform 0.3s;
    }
    .card:hover {
      transform: translateY(-5px);
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }
  `]
})
export class HomeComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
  }
}
