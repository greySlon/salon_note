import React, {Component} from 'react';

export default class WorkitemUpdate extends Component {
  constructor(props) {
    super(props);
    this.state = {
      workitem: this.props.workitem
    };
    this.procedureDictionary = [
      {
        "id": 1,
        "procedure_name": "shellac"
      },
      {
        "id": 2,
        "procedure_name": "shellac remove"
      },
      {
        "id": 3,
        "procedure_name": "reballancing"
      }
    ];
    this.addProcedure = this.addProcedure.bind(this);
    this.removeProcedure = this.removeProcedure.bind(this);
    this.updateOnServer = this.updateOnServer.bind(this);
  }

  renderSelect(masters) {
    return (
        <select>
          {masters.map((item, i) =>
              <option key={i} value={item.id}>{item.first_name}</option>)}
        </select>
    )
  }

  renderUl(className, procedureNames, onClickCallback) {
    return (
        <ul className={className}>
          {
            procedureNames.map((item, i) =>
                <li key={i}>
                  <button
                      onClick={onClickCallback}>{item}</button>
                </li>
            )
          }
        </ul>
    )
  }

  convertDate(serverDate) {
    let split = serverDate.split("-");
    return split[2] + "-" + split[1] + "-" + split[0];
  }

  addProcedure(e) {
    const procedureName = e.target.textContent;
    this.state.workitem.procedures = this.state.workitem.procedures.concat(
        this.procedureDictionary.filter(
            item => item.procedure_name == procedureName));
    this.setState({
      workitem: this.state.workitem
    });
  }

  removeProcedure(e) {
    const procedureName = e.target.textContent;
    const procedureList = this.state.workitem.procedures.filter(
        item => item.procedure_name != procedureName);
    this.state.workitem.procedures = procedureList;
    this.setState({workitem: this.state.workitem});
  }

  updateOnServer() {
    //todo fetch() => this.state.workitem
    this.props.onFinish();
  }

  render() {
    const masters = this.props.masters;
    const {comment, service_time, service_date, procedures, client_detales} = this.state.workitem;
    console.log(client_detales.name + "-" + client_detales.phones);
    return (
        <div className="workitem-container block">

          <div>
            <div className="time-container block">
              {this.renderSelect(masters)}
              <input type="date"
                     defaultValue={this.convertDate(service_date)}/>
              <input type="time"
                     onChange={e => console.log(e.target.value)}
                     defaultValue={service_time}/>
            </div>
            <div className="procedures-container block">
              {this.renderUl("procedures-all",
                  this.procedureDictionary.map(item => item.procedure_name),
                  this.addProcedure)}
              {this.renderUl("procedures-add",
                  procedures.map(item => item.procedure_name),
                  this.removeProcedure)}
            </div>
          </div>

          <div className="comment-container block">
            <textarea placeholder="Comments">{comment}</textarea>
            <div className="phone-container block">
              <div className={"time"}>{client_detales.name}</div>
              <input type="text" defaultValue={client_detales.phones}/>
              <button onClick={this.updateOnServer}>Update</button>
            </div>
          </div>

        </div>
    );
  }
}