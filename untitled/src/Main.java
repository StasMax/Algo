import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

}

public interface Processor {
    void process(String rs);
    BankType getBankType();
}

public enum BankType {
    ALFA,
        SBER,
        TINKOFF;
        }

public class TinkoffProcessor implements Processor {

    @Override
    public void process() {

    }

    public class AlfaProcessor implements Processor {

        @Override
        public void process() {

        }
}


    public class SberProcessor implements Processor {

        @Override
        public void process() {

        }
    }


    public class BankStatementScheduler{

        @Autowired
        List<Processor>processors;

        @Autowired
        BankService bankService;

        @Sceduler
        public void scheduler() {
            List<RsInfoEntity> all =  bankService.findAll();

            all.forEach(rs -> {
                processors.forEach(processor -> {
                    if (processor.getBankType().equals(rs.bankType)) {
                        processor.process(rs.rs);
                    }
                });
            });


        }
    }

    public class BankService{

        BankRepository repository;

        public RsInfoEntity getEntityByRs(String rs) {
           return repository.getByRs(rs);
        }

        public List<RsInfoEntity> getAllEntitiesByRs(BankType bankType) {
            return repository.getByRs(rs);
        }

        fiddAll
    }

    public interface BankRepository{
        RsInfoEntity getByRs(String rs);


    }

    public class RsInfoEntity{
        private BankType bankType;
        private String rs;
    }
}