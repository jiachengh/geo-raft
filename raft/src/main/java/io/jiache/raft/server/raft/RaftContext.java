package io.jiache.raft.server.raft;

import io.jiache.raft.common.Address;
import io.jiache.raft.server.storage.Log;
import io.jiache.raft.server.storage.StateMachine;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RaftContext {
    private final Integer memberId;
    private final Map<Integer, Address> allPeers;
    private final Map<Integer, Address> allClients;
    private final Log log;
    private final StateMachine stateMachine;
    private final RaftConfiguration configuration;
    private Integer leaderId;
    private Long term;
    private Long lastCommitIndex;
    private Long lastApplyIndex;
    private Integer voteFor;
    private Duration lastReceiveHeartBeatTime;
    private State state;
    private List<Address> secretaries;

    private RaftContext(
            Integer memberId,
            Map<Integer, Address> allPeers,
            Map<Integer, Address> allClients,
            Log log,
            StateMachine stateMachine,
            RaftConfiguration configuration,
            Integer leaderId,
            Long term,
            Long lastCommitIndex,
            Long lastApplyIndex,
            Integer voteFor,
            Duration lastReceiveHeartBeatTime,
            State state,
            List<Address> secretaries) {
        this.memberId = memberId;
        this.allPeers = allPeers;
        this.allClients = allClients;
        this.log = log;
        this.stateMachine = stateMachine;
        this.configuration = configuration;
        this.leaderId = leaderId;
        this.term = term;
        this.lastCommitIndex = lastCommitIndex;
        this.lastApplyIndex = lastApplyIndex;
        this.voteFor = voteFor;
        this.lastReceiveHeartBeatTime = lastReceiveHeartBeatTime;
        this.state = state;
        this.secretaries = secretaries;
    }

    public Builder newBuilder() {
        return new Builder();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public Map<Integer, Address> getAllPeers() {
        return allPeers;
    }

    public Map<Integer, Address> getAllClients() {
        return allClients;
    }

    public Log getLog() {
        return log;
    }

    public StateMachine getStateMachine() {
        return stateMachine;
    }

    public RaftConfiguration getConfiguration() {
        return configuration;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public Long getTerm() {
        return term;
    }

    public Long getLastCommitIndex() {
        return lastCommitIndex;
    }

    public Long getLastApplyIndex() {
        return lastApplyIndex;
    }

    public Integer getVoteFor() {
        return voteFor;
    }

    public Duration getLastReceiveHeartBeatTime() {
        return lastReceiveHeartBeatTime;
    }

    public State getState() {
        return state;
    }

    public synchronized void setLastReceiveHeartBeatTime(Duration lastReceiveHeartBeatTime) {
        this.lastReceiveHeartBeatTime = lastReceiveHeartBeatTime;
    }

    public static class Builder implements io.jiache.raft.common.Builder<RaftContext> {
        private Integer memberId;
        private Map<Integer, Address> allPeers;
        private Map<Integer, Address> allClients;
        private Log log;
        private StateMachine stateMachine;
        private RaftConfiguration configuration;
        private Integer leaderId;
        private Long term;
        private Long lastCommitIndex;
        private Long lastApplyIndex;
        private Integer voteFor;
        private Duration lastReceiveHeartBeatTime;
        private State state;
        private List<Address> secretaries;

        public Builder setMemberId(Integer memberId) {
            this.memberId = memberId;
            return this;
        }

        public void setAllPeers(Map<Integer, Address> allPeers) {
            this.allPeers = allPeers;
        }

        public void setAllClients(Map<Integer, Address> allClients) {
            this.allClients = allClients;
        }

        public Builder setLog(Log log) {
            this.log = log;
            return this;
        }

        public Builder setStateMachine(StateMachine stateMachine) {
            this.stateMachine = stateMachine;
            return this;
        }

        public Builder setConfiguration(RaftConfiguration configuration) {
            this.configuration = configuration;
            return this;
        }

        public Builder setLeaderId(Integer leaderId) {
            this.leaderId = leaderId;
            return this;
        }

        public Builder setTerm(Long term) {
            this.term = term;
            return this;
        }

        public Builder setLastCommitIndex(Long lastCommitIndex) {
            this.lastCommitIndex = lastCommitIndex;
            return this;
        }

        public Builder setLastApplyIndex(Long lastApplyIndex) {
            this.lastApplyIndex = lastApplyIndex;
            return this;
        }

        public Builder setVoteFor(Integer voteFor) {
            this.voteFor = voteFor;
            return this;
        }

        public Builder setLastReceiveHeartBeatTime(Duration lastReceiveHeartBeatTime) {
            this.lastReceiveHeartBeatTime = lastReceiveHeartBeatTime;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setSecretaries(List<Address> secretaries) {
            this.secretaries = secretaries;
            return this;
        }

        @Override
        public RaftContext build() {
            return new RaftContext(
                    memberId,
                    allPeers,
                    allClients,
                    log,
                    stateMachine,
                    configuration,
                    leaderId,
                    term,
                    lastCommitIndex,
                    lastApplyIndex,
                    voteFor,
                    lastReceiveHeartBeatTime,
                    state,
                    secretaries);
        }
    }
}
