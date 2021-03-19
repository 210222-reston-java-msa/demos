import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { AngularFireDatabase } from 'angularfire2/database';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
// this is responsible for fetching an observabe (a stream of values FROM the database)
  constructor(private db: AngularFireDatabase) { }

  getContent(): Observable<any> {
    const ref = this.db.object('alerts/testAlert');
    return ref.valueChanges();
  }

}
