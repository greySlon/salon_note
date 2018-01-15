import React from 'react';
import Day from './Day';
import $$restapi from './$$restapi.js';

export default class Week extends React.Component {
  constructor(props) {
    super(props);
    this.getNextWeek = this.getNextWeek.bind(this);
    this.getPrevWeek = this.getPrevWeek.bind(this);
    this.refreshSchedule = this.refreshSchedule.bind(this);
    this.state = {
      masterId: this.props.masterid,
      schedule: []
    };
    this.api = new $$restapi();
    this.offset = 0;
    this.refreshSchedule();
  }

  componentWillReceiveProps(nextProps) {
    this.state.masterId = nextProps.masterid;
    this.api.doGetRequest(r => this.setState({schedule: r})
        , this.buildUrl(nextProps.masterid, this.offset));
  }

  getNextWeek() {
    this.api.doGetRequest(r => this.setState({schedule: r})
        , this.buildUrl(this.state.masterId, ++this.offset));
  }

  getPrevWeek() {
    this.api.doGetRequest(r => this.setState({schedule: r})
        , this.buildUrl(this.state.masterId, --this.offset));
  }

  buildUrl(id, offset) {
    return "/workitem/get_week?" + 'master_id=' + id + '&week_offset=' + offset;
  }

  refreshSchedule() {
    this.api.doGetRequest(r => {
          console.log("refresh set state");
          console.table(r);
          this.setState({schedule: r})
        }
        , this.buildUrl(this.state.masterId, this.offset));
    console.log("refresh week");
  }

  render() {
    const schedule = this.state.schedule;
    const masters = this.props.masters;
    const days = ["Понедельник", "Вторник", "Среда", "Четверг", "Пятница",
      "Суббота", "Воскресенье"];

    console.log("Week#render.masterId:" + this.state.masterId + "; offset:"
        + this.offset);
    console.table(schedule);

    if (schedule.length == 0) {
      return "";
    }
    return (
        <div>
          <input type="button" value={"Prev"} onClick={this.getPrevWeek}/>
          <input type="button" value={"Next"} onClick={this.getNextWeek}/>
          {days.map(
              (day, i) => <Day key={i}
                               day={day}
                               date={schedule[i].service_date}
                               workitems={schedule[i].workitems}
                               masters={masters}
                               onChange={this.refreshSchedule}/>
          )}
        </div>
    )
  }
}