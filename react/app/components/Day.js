import React from 'react';
import AddWorkitem from './AddWorkitem';
import WorkItem from './WorkItem';

export default class Day extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      buttonName: "Add",
      add: false
    }
    this.onCancel = this.onCancel.bind(this);
    this.refresh = this.refresh.bind(this);
    console.log("DAY CONSTRUCT")
  }

  onCancel() {
    console.log("cancel setstate this.state.add:" + this.state.add)
    if (this.state.add) {
      this.setState({add: false, buttonName: "Add"});
      console.log("cancel setstate this.state.add:" + this.state.add)
    } else {
      this.setState({add: true, buttonName: "Cancel"});
      console.log("cancel setstate this.state.add:" + this.state.add)
    }
  }

  refresh() {
    this.onCancel();
    this.props.onChange();
  }

  // componentDidUpdate(prevProps, prevState) {
  //   this.state.add = false;
  //   this.state.buttonName = "Add";
  // }

  render() {
    console.log("DAY RENDERING.state.add:" + this.state.add)
    const workitems = this.props.workitems;
    const masters = this.props.masters;
    const addBlock = this.state.add
        ? <AddWorkitem ref="addWi" masters={masters} onSave={this.refresh}/>
        : null;
    return (
        <div className="schedule">
          <h2>
            {this.props.day} ({this.props.date})
            <button className="add-workitem"
                    onClick={this.onCancel}>{this.state.buttonName}</button>
          </h2>
          {addBlock}
          {
            workitems.map((workitem, i) =>
                <WorkItem onChange={this.props.onChange}
                          key={i}
                          workitem={workitem}
                          masters={masters}/>
            )
          }
        </div>
    )
  }
}