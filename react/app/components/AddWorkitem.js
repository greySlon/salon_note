import React from 'react';
import ProcedureList from './ProcedureList';
import _Select from './_Select';
import SimpleSelect from './SimpleSelect';
import $$restapi from './$$restapi.js';

export default class AddWorkitem extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      addProcedures: [],
      allProcedures: [],
      contactList: [],
      contacts: "",
      phone: "",
      name: ""
    }
    this.addProcedure = this.addProcedure.bind(this);
    this.removeProcedure = this.removeProcedure.bind(this);
    this.addWorkitem = this.addWorkitem.bind(this);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangePhone = this.onChangePhone.bind(this);
    this.onSelect = this.onSelect.bind(this);
    this.api = new $$restapi();
    this.api.doGetRequest(r => this.setState({allProcedures: r}),
        "/procedure/actual");
  }

  addProcedure(e) {
    const procedureName = e.target.textContent;
    this.state.addProcedures = this.state.addProcedures.concat(
        this.state.allProcedures.filter(
            item => item.procedure_name == procedureName));
    this.setState({addProcedures: this.state.addProcedures});
  }

  removeProcedure(e) {
    const procedureName = e.target.textContent;
    this.setState({
      addProcedures: this.state.addProcedures
      .filter(item => item.procedure_name != procedureName)
    });
  }

  getFormattedDate() {
    let date = new Date();

    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    if (month < 10) {
      month = "0" + month;
    }
    if (day < 10) {
      day = "0" + day;
    }
    return year + "-" + month + "-" + day;
  }

  addWorkitem() {
    let workitem = {};
    workitem.service_date;
    workitem.service_time;
    workitem.master_id;
    workitem.comment;
    workitem.procedures;
    workitem.client_info;
    // this.api.doPostRequest(workitem
    //     , "/workitem/add"
    //     , this.props.onSave);
    //     , false
    console.log("ADD")
    // console.log(this.refs.simpleSelect.selected)
    console.table(workitem);
    this.props.onSave();
  }

  onSelect(value) {
    console.log(value);
    let split = value.split("-");
    this.refs.iname.value = split[0];
    this.refs.iphone.value = split[1];
    this.setState({
      name: split[0],
      phone: split[1]
    });
  }

  onChangeName(e) {
    let value = e.target.value;
    this.api.doPostRequest("part=" + value, "/utils/contact_by_name", true,
        list => {
          this.setState({
            contactList: JSON.parse(list).map(
                pair => pair.name + " - " + pair.phone)
          })
        });
    this.setState(
        {
          name: e.target.value
        });
  }

  onChangePhone(e) {
    let value = e.target.value;
    this.api.doPostRequest("part=" + value, "/utils/contact_by_phone", true,
        list => {
          this.setState({
            contactList: JSON.parse(list).map(
                pair => pair.name + " - " + pair.phone)
          })
        });
    this.setState(
        {
          phone: e.target.value
        });
  }

  render() {
    const masters = this.props.masters;
    let now = new Date();
    console.table(masters);
    console.table(this.state.allProcedures)
    console.table(this.state.addProcedures)
    return (
        <div className="workitem-container border-container">

          <div className="border-container">
            <div className="time-container block">
              <_Select ref="masterSelected" map={masters.map((item) => {
                    return {value: item.id, key: item.first_name}
                  }
              )}/>
              <input type="date" ref="dateIn"
                     defaultValue={this.getFormattedDate()}/>
              <input type="time" ref="timeIn" defaultValue={"11:00"}/>
            </div>
            <div className="procedures-container block">
              <ProcedureList list={this.state.allProcedures.map(
                  item => item.procedure_name)}
                             callback={this.addProcedure}/>
              <ProcedureList list={this.state.addProcedures.map(
                  item => item.procedure_name)}
                             callback={this.removeProcedure}/>
            </div>
          </div>

          <div className="comment-container border-container">
            <div className="block">
              <div style={{display: "flex", flexDirection: "column"}}>
                <input type="text"
                       ref="iname"
                       onChange={this.onChangeName}
                       placeholder="Name"/>
                <input type="phone"
                       ref="iphone"
                       onChange={this.onChangePhone}
                       placeholder="Phone"/>
                <SimpleSelect onSelect={this.onSelect}
                              list={this.state.contactList}/>
              </div>
            </div>
            <button style={{margin: "5px"}} onClick={this.addWorkitem}>Add
            </button>
          </div>

        </div>
    )
  }
}